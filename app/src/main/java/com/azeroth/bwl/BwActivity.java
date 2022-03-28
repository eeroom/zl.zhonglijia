package com.azeroth.bwl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.azeroth.utility.FunctionWrapper;
import com.azeroth.utility.RunnableWithException;

import org.json.JSONArray;
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
public abstract class BwActivity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.initView();
        }catch (Exception ex){
            String msg=ex.getMessage();
            String msg2=ex.getClass().getName();
            StackTraceElement[] st=ex.getStackTrace();
            for (int i=0;i<st.length;i++){
                Log.e("WP",st[i].toString());
            }

            Toast.makeText(this,msg!=null?msg:msg2,Toast.LENGTH_SHORT).show();
        }
    }

    Handler handler=new Handler();

    public abstract void initData() throws Exception;
    public abstract void initView() throws Exception;
    protected View.OnClickListener wrapperOnclickListener(FunctionWrapper<View> fn){
        return x->{
          try {
              fn.invoke(x);
          }catch (Exception ex){
              String msg=ex.getMessage();
              String msg2=ex.getClass().getName();
              StackTraceElement[] st=ex.getStackTrace();
              for (int i=0;i<st.length;i++){
                  Log.e("WP",st[i].toString());
              }
              this.handler.post(()->Toast.makeText(this,msg!=null?msg:msg2,Toast.LENGTH_SHORT).show());
          }
        };
    }

    public Runnable  wrapperRunnable(RunnableWithException fn) {
        return  ()->{
            try {
                fn.Run();
            }catch (Exception ex){
                StackTraceElement[] st=ex.getStackTrace();
                for (int i=0;i<st.length;i++){
                    Log.e("WP",st[i].toString());
                }
                String msg=ex.getMessage();
                String msg2=ex.getClass().getName();
                this.handler.post(()->Toast.makeText(this,msg!=null?msg:msg2,Toast.LENGTH_SHORT).show());
            }
        };
    }

   public void SendHttpRequestByGET(HttpRequestMessage message, FunctionWrapper<String> fn)throws  Exception{
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

   public void SendHttpRequestByPOST2(HttpRequestMessage message, FunctionWrapper<String> fn)throws  Exception {
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
            if(message.beforSend!=null)
                message.beforSend.run(connection,message);
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

    public void SendSoapRequest(SoapRequestMessage message, Action2ThrowException<String,String> fn)throws  Exception{
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
            responseValue = (SoapObject) responseValue.getProperty(message.action + "Result");
            String json1 = responseValue.getProperty(0).toString();
            String json2 = responseValue.getProperty(1).toString();
//            JSONArray obj_json1 = new JSONArray(json1);
//            String resultStr = obj_json1.getJSONObject(0).getString("result");
//            String codeStr=obj_json1.getJSONObject(0).getString("Code");
            this.handler.post(this.wrapperRunnable(()->fn.run(json1,json2)));
        })).start();
    }

    public int dip2px(Context context, float dipValue){
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }
}
