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
import com.azeroth.model.SpBucket;
import com.azeroth.model.UserInfo;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

import com.azeroth.utility.*;


public class LoginActivity extends BwActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = this.getSharedPreferences(SpBucket.Index.Login, MODE_PRIVATE);
        String userInfo = sp.getString(SpBucket.Item.UserInfo, "");
        if (!userInfo.isEmpty()) {
            this.redirectToHome(userInfo);
            return;
        }
        setContentView(R.layout.activity_login);
        this.findViewById(R.id.loginBtnOk).setOnClickListener(this.wrapperOnclickListener(x -> this.loginBtnOkOnClick(x)));
    }

    private void loginBtnOkOnClick(View view) throws Exception {
        HttpRequestMessage message = new HttpRequestMessage("http://192.168.23.231:3161/Home/Login");
        UserInfo userInfo = new UserInfo();
        userInfo.CellPhoneNumber = ((TextView) this.findViewById(R.id.loginTxtName)).getText().toString();
        userInfo.Pwd = ((TextView) this.findViewById(R.id.loginTxtPassword)).getText().toString();
        if (userInfo.CellPhoneNumber.isEmpty())
            throw new RuntimeException("手机号码不能为空");
        if (userInfo.Pwd.isEmpty())
            throw new RuntimeException("密码不能为空");
        message.parameter = userInfo;
        this.sendHttpRequestByPOST(message, x -> {
            RT<UserInfo> rt = com.alibaba.fastjson.JSON.parseObject(x, new TypeReference<RT<UserInfo>>() {
            });
            if (rt.codej != RT.RTCode.Ok) {
                Toast.makeText(this, rt.msg, Toast.LENGTH_SHORT).show();
                return;
            }
            this.redirectToHome(x);
        });
    }

    private void loginBtnOkOnClick2(View view) throws Exception {
        SoapRequestMessage message = new SoapRequestMessage("");
        message.parameter.put("phone", ((TextView) this.findViewById(R.id.loginTxtName)).getText().toString());
        message.parameter.put("password", ((TextView) this.findViewById(R.id.loginTxtPassword)).getText().toString());
        message.action = "USERLOGIN";

        this.SendSoapRequest(message, result -> {

            SoapObject provinceSoapObject = (SoapObject) result.getProperty(message.action + "Result");
            String json = provinceSoapObject.getProperty(0).toString();
            String json2 = provinceSoapObject.getProperty(1).toString();
            org.json.JSONArray obj_json1 = new org.json.JSONArray(json);
            String str = obj_json1.getJSONObject(0).getString("result");
            if (!str.equals("0"))
                throw new RuntimeException(json2);
            SharedPreferences sp = this.getSharedPreferences(SpBucket.Index.Login, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(SpBucket.Item.UserInfo, json2);
            editor.commit();
            this.redirectToHome(json2);
        });
    }

    private void redirectToHome(String userInfo) {
        Intent it = new Intent();
        it.setClass(this, HomeActivity.class);
        it.putExtra(SpBucket.Item.UserInfo, com.alibaba.fastjson.JSON.toJSONString(userInfo));
        this.startActivity(it);
        this.finish();
    }
}
