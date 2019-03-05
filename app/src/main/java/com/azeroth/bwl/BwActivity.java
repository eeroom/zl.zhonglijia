package com.azeroth.bwl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.azeroth.utility.FunctionWrapper;
import com.azeroth.utility.RunnableWithException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import com.azeroth.utility.*;
public class BwActivity extends Activity
{
    Handler handler=new Handler();

    protected View.OnClickListener wrapperOnclickListener(FunctionWrapper<View> fn){
        return x->{
          try {
              fn.invoke(x);
          }catch (Exception ex){
              String msg=ex.getMessage();
              if(msg==null ||msg.isEmpty())
                  msg=ex.getClass().getName();
              final  String msgtmp=msg;
              this.handler.post(()->Toast.makeText(this,msgtmp,Toast.LENGTH_SHORT).show());
          }
        };
    }

    public Runnable  wrapperRunnable(RunnableWithException fn) {
        return  ()->{
            try {
                fn.Run();
            }catch (Exception ex){
                String msg=ex.getMessage();
                if(msg==null ||msg.isEmpty())
                    msg=ex.getClass().getName();
                final  String msgtmp=msg;
                this.handler.post(()->Toast.makeText(this,msgtmp,Toast.LENGTH_SHORT).show());
            }
        };
    }

    void SendHttpRequestByGET(HttpRequestMessage message, FunctionWrapper<String> fn)throws  Exception{
        new Thread(this.wrapperRunnable(()->{
            URL url=new URL(message.url);
            java.net.HttpURLConnection connection= (java.net.HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setUseCaches(false);
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream(),message.responseCharsetName));
            StringBuilder sb=new StringBuilder();
            String line=null;
            while (( line= reader.readLine()) != null ) {
                sb.append(line);
            }
            this.handler.post(this.wrapperRunnable(()->fn.invoke(sb.toString())));
        })).start();
    }

    void SendHttpRequestByPOST2(HttpRequestMessage message, FunctionWrapper<String> fn)throws  Exception {
        new Thread(this.wrapperRunnable(()->{
            URL url=new URL(message.url);
            java.net.HttpURLConnection connection= (java.net.HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setUseCaches(false);
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream(),message.responseCharsetName));
            StringBuilder sb=new StringBuilder();
            String line=null;
            while (( line= reader.readLine()) != null ) {
                sb.append(line);
            }
            this.handler.post(this.wrapperRunnable(()->fn.invoke(sb.toString())));
        })).start();
    }

    public void sendHttpRequestByPOST(HttpRequestMessage message,FunctionWrapper<String> fn) throws  Exception {
        new Thread(this.wrapperRunnable(()->{
            URL url=new URL(message.url);
            java.net.HttpURLConnection connection= (java.net.HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            // POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String json = com.alibaba.fastjson.JSON.toJSONString(message.parameter);
            out.writeBytes(json);
            out.flush();
            out.close();
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lineTxt;
            StringBuffer sb = new StringBuffer();
            while ((lineTxt = reader.readLine()) != null) {
                sb.append(lineTxt);
            }
            String rt=sb.toString();
            reader.close();
            // 断开连接
            connection.disconnect();
            this.handler.post(this.wrapperRunnable(()->fn.invoke(rt)));
        })).start();
    }

    public void SendSoapRequest(SoapRequestMessage message, FunctionWrapper<SoapObject> fn)throws  Exception{
        new Thread(this.wrapperRunnable(()->{
            // 创建SoapObject对象
            SoapObject requestParameter = new SoapObject(message.namesp, message.action);
            // SoapObject添加参数
            HashMap<String,String> parameter=message.parameter;
            for (Iterator<Map.Entry<String, String>> it = parameter.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                requestParameter.addProperty(entry.getKey(), entry.getValue());
            }
            // 实例化SoapSerializationEnvelope，传入WebService的SOAP协议的版本号
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(message.soapVersion);
            // 设置是否调用的是.Net开发的WebService
            soapEnvelope.setOutputSoapObject(requestParameter);
            soapEnvelope.dotNet = true;
            HttpTransportSE httpTransportSE = new HttpTransportSE(message.url, message.requestTimeout);
            httpTransportSE.debug = true;
            httpTransportSE.call(message.namesp + message.action, soapEnvelope);
            soapEnvelope.getResponse();
            SoapObject responseValue=(SoapObject) soapEnvelope.bodyIn;
            this.handler.post(this.wrapperRunnable(()->fn.invoke(responseValue)));
        })).start();
    }

    public int dip2px(Context context, float dipValue){
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }
}
