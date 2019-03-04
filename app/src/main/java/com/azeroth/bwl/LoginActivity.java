package com.azeroth.bwl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.ksoap2.serialization.SoapObject;

import java.io.BufferedReader;
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

    private void loginBtnOkOnClick(View view) throws  Exception {
        SoapRequestMessage message=new SoapRequestMessage(API.ERPWebService.BaseAddress);
        message.SetParameter("phone", ((TextView)this.findViewById(R.id.loginTxtName)).getText().toString());
        message.SetParameter("password", ((TextView)this.findViewById(R.id.loginTxtPassword)).getText().toString());
        message.setAction(API.ERPWebService.Action.USERLOGIN);
        this.SendSoapRequest(message,result->{

            SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERPWebService.Action.USERLOGIN + "Result");
            String json = provinceSoapObject.getProperty(0).toString();
            String json2 = provinceSoapObject.getProperty(1).toString();
            JSONArray obj_json1 = new JSONArray(json);
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
