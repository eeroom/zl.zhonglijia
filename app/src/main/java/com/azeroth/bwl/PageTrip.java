package com.azeroth.bwl;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.azeroth.model.BwMessage;
import com.azeroth.utility.HttpRequestMessage;

import java.util.ArrayList;

public class PageTrip extends Page {
    public PageTrip(BwActivity activity){
        super(activity,View.inflate(activity,R.layout.page_trip,null));
    }

    @Override
    public void initData() throws Exception {

    }

    @Override
    public void initView() throws Exception {
        this.view.findViewById(R.id.tripBtnOk).setOnClickListener(this.hostActivity.wrapperOnclickListener(this::tripBtnOkOnclick));
    }

    void tripBtnOkOnclick(View view) throws  Exception{
        String target= ((EditText)this.view.findViewById(R.id.tripjpTarget)).getText().toString();
        String value=((EditText)this.view.findViewById(R.id.tripjpContent)).getText().toString();
        BwMessage message=new BwMessage();
        message.platform=new ArrayList<>();
        message.platform.add("android");
        message.audience=new BwMessage.Audience();
        message.audience.alias =new ArrayList<>();
        message.audience.alias.add(target);
        message.notification=new BwMessage.Notification();
        message.notification.alert=value;

        HttpRequestMessage requestMessage=new HttpRequestMessage("https://api.jpush.cn/v3/push");
        requestMessage.parameter=message;

        String head="9f0a815b67b7dd507d52f2dc"+":"+"b8a2bc3d1f5897f1ec25b15f";
        //String head2= Base64.getEncoder().encodeToString(head.getBytes("utf-8"));
       String head2=  com.azeroth.utility.BBase64.encode(head.getBytes("utf-8"));
        requestMessage.beforSend=(x,y)->{
            x.setRequestProperty("Authorization","Basic "+head2);
        };
        this.hostActivity.sendHttpRequestByPOST(requestMessage,this::aftersendMessage);
    }

    void aftersendMessage(String rt){
        Toast.makeText(this.hostActivity,rt,Toast.LENGTH_SHORT).show();
    }
}
