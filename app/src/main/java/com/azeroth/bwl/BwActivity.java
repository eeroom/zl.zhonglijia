package com.azeroth.bwl;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

import static android.provider.ContactsContract.CommonDataKinds.Identity.NAMESPACE;

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
              this.handler.post(()->Toast.makeText(this,msgtmp,Toast.LENGTH_LONG).show());
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
                this.handler.post(()->Toast.makeText(this,msgtmp,Toast.LENGTH_LONG).show());
            }
        };
    }

    public void SendHttpRequestByGET(HttpRequestMessage message, FunctionWrapper<String> fn)throws  Exception{
        new Thread(this.wrapperRunnable(()->{
            URL url=new URL(message.getUrl());
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

    public void SendHttpRequestByPOST(HttpRequestMessage message, FunctionWrapper<String> fn)throws  Exception {
        new Thread(this.wrapperRunnable(()->{
            URL url=new URL(message.getUrl());
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

    public void SendSoapRequest(SoapRequestMessage message, FunctionWrapper<SoapObject> fn)throws  Exception{
        new Thread(this.wrapperRunnable(()->{
            // 创建SoapObject对象
            SoapObject requestParameter = new SoapObject(message.namesp, message.action);
            // SoapObject添加参数
            HashMap<String,String> parameter=message.getParameter();
            for (Iterator<Map.Entry<String, String>> it = parameter.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                requestParameter.addProperty(entry.getKey(), entry.getValue());
            }
            // 实例化SoapSerializationEnvelope，传入WebService的SOAP协议的版本号
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(message.soapVersion);
            // 设置是否调用的是.Net开发的WebService
            soapEnvelope.setOutputSoapObject(requestParameter);
            soapEnvelope.dotNet = true;
            HttpTransportSE httpTransportSE = new HttpTransportSE(message.getUrl(), message.requestTimeout);
            httpTransportSE.debug = true;
            httpTransportSE.call(message.namesp + message.action, soapEnvelope);
            soapEnvelope.getResponse();
            SoapObject responseValue=(SoapObject) soapEnvelope.bodyIn;
            this.handler.post(this.wrapperRunnable(()->fn.invoke(responseValue)));
        })).start();
    }
}
