package com.azeroth.bwl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.UserInfo;

import org.ksoap2.serialization.SoapObject;

import java.util.List;


public class LoginActivity extends BwActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp= this.getSharedPreferences("userInfo",MODE_PRIVATE);
        String userInfo= sp.getString(SpKey.userInfo,"");
        if(!userInfo.isEmpty()){
            this.redirectToHome(userInfo);
            return;
        }
        setContentView(R.layout.activity_login);
        this.findViewById(R.id.loginBtnOk).setOnClickListener(this.wrapperOnclickListener(x->this.loginBtnOkOnClick(x)));
    }

    private  void  loginBtnOkOnClick(View view) throws  Exception{
        HttpRequestMessage message=new HttpRequestMessage("http://192.168.23.231:3161/Home/Login");
        UserInfo userInfo=new UserInfo();
        userInfo.CellPhoneNumber=((TextView)this.findViewById(R.id.loginTxtName)).getText().toString();
        userInfo.Pwd=((TextView)this.findViewById(R.id.loginTxtPassword)).getText().toString();
        message.parameter=userInfo;
        this.sendHttpRequestByPOST(message,x->{
            RT<UserInfo> rt= com.alibaba.fastjson.JSON.parseObject(x,new TypeReference<RT<UserInfo>>() {});

            Toast.makeText(this,rt.data.NickName,Toast.LENGTH_LONG).show();
        });
    }

    private void loginBtnOkOnClick2(View view) throws  Exception {
        SoapRequestMessage message=new SoapRequestMessage(API.ERPWebService.BaseAddress);
        message.parameter.put("phone", ((TextView)this.findViewById(R.id.loginTxtName)).getText().toString());
        message.parameter.put("password", ((TextView)this.findViewById(R.id.loginTxtPassword)).getText().toString());
        message.action=API.ERPWebService.Action.USERLOGIN;

        this.SendSoapRequest(message,result->{

            SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERPWebService.Action.USERLOGIN + "Result");
            String json = provinceSoapObject.getProperty(0).toString();
            String json2 = provinceSoapObject.getProperty(1).toString();
            org.json.JSONArray obj_json1 = new org.json.JSONArray(json);
            String str = obj_json1.getJSONObject(0).getString("result");
            if(!str.equals(API.Code.Ok))
                throw new RuntimeException(json2);
            SharedPreferences sp= this.getSharedPreferences("userInfo",MODE_PRIVATE);
            SharedPreferences.Editor editor= sp.edit();
            editor.putString(SpKey.userInfo,json2);
            editor.commit();

            List<RT> lst= JSON.parseArray(json2,RT.class);

            this.redirectToHome(json2);
        });
    }

    private void redirectToHome(String userInfo){
        Intent it=new Intent();
        it.setClass(this,HomeActivity.class);
        it.putExtra(SpKey.userInfo,userInfo);
        this.startActivity(it);
        this.finish();
    }
}
