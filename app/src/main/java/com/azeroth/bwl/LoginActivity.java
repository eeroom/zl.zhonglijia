package com.azeroth.bwl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.SpBucket;
import com.azeroth.model.UserInfo;

import org.json.JSONArray;
import org.ksoap2.serialization.SoapObject;

import com.azeroth.utility.*;


public class LoginActivity extends BwActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void initView() throws Exception {
        SharedPreferences sp = this.getSharedPreferences(SpBucket.Index.Global, MODE_PRIVATE);
        String userInfoJson = sp.getString(SpBucket.Item.UserInfo, "");
        if (userInfoJson!="") {
            BwApplication.appInstance.userInfo=com.alibaba.fastjson.JSON.parseObject(userInfoJson,UserInfo.class);
            this.redirectToHome();
            return;
        }
        setContentView(R.layout.activity_login);
        this.findViewById(R.id.loginBtnOk).setOnClickListener(this.wrapperOnclickListener(x -> this.loginBtnOkOnClick(x)));

        this.initData();
    }

    @Override
    public void initData() throws Exception {

    }



    private void loginBtnOkOnClick(View view) throws Exception {
        view.setEnabled(false);
        SoapRequestMessage message=new SoapRequestMessage(API.ERP.BassAdrees);
        message.action=API.ERP.Action.USERLOGIN;
        BwApplication.appInstance.userInfo.PhoneNumber=((TextView) this.findViewById(R.id.loginTxtName)).getText().toString();
        BwApplication.appInstance.userInfo.Pwd=((TextView) this.findViewById(R.id.loginTxtPassword)).getText().toString();
        message.parameter.put("phone",  BwApplication.appInstance.userInfo.PhoneNumber);
        message.parameter.put("password",BwApplication.appInstance.userInfo.Pwd);
        this.SendSoapRequest(message,(rtcode,value)->{
            view.setEnabled(true);
            String resultStr = new JSONArray(rtcode).getJSONObject(0).getString("result");
            if(!"1".equals(resultStr)){
                Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
                return;
            }
            JSONArray obj_json2 = new JSONArray(value);
            BwApplication.appInstance.userInfo.TR_NAME = obj_json2.getJSONObject(0).getString("TR_NAME");
            BwApplication.appInstance.userInfo.TR_UNION_ID = obj_json2.getJSONObject(0).getString("TR_UNION_ID");
            BwApplication.appInstance.userInfo.TR_HEADIMGURL = obj_json2.getJSONObject(0).getString("TR_HEADIMGURL");
            BwApplication.appInstance.userInfo.UR_TYPE= obj_json2.getJSONObject(0).getString("UR_TYPE");
            BwApplication.appInstance.userInfo.module = obj_json2.getJSONObject(0).getString("module");
            BwApplication.appInstance.userInfo.privilege = obj_json2.getJSONObject(0).getString("privilege");
            BwApplication.appInstance.userInfo.UR_DEPARTMENT_ID= obj_json2.getJSONObject(0).getString("UR_DEPARTMENT_ID");
            BwApplication.appInstance.userInfo.UR_USER_ID = obj_json2.getJSONObject(0).getString("UR_USER_ID");
            BwApplication.appInstance.userInfo.PhoneToken = obj_json2.getJSONObject(0).getString("PhoneToken");
            SharedPreferences sp = this.getSharedPreferences(SpBucket.Index.Global, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(SpBucket.Item.UserInfo, com.alibaba.fastjson.JSON.toJSONString(BwApplication.appInstance.userInfo));
            editor.commit();
            this.redirectToHome();
        });
    }

    private void redirectToHome() {
        cn.jpush.android.api.JPushInterface.setAlias(BwApplication.appInstance,1,BwApplication.appInstance.userInfo.PhoneNumber);
        Intent it = new Intent();
        it.setClass(this, MainActivity.class);
        this.startActivity(it);
        this.finish();
    }


}
