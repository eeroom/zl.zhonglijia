package com.azeroth.utility;

import com.baidu.location.BDLocation;

public class BwLocationListener extends com.baidu.location.BDAbstractLocationListener {

    public Action2<BDLocation,Integer> handlerReceiveLocation;
    Integer tickTimes=0;
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if(this.handlerReceiveLocation==null)
            return;
        this.tickTimes++;
        if(this.tickTimes>100)
            this.tickTimes=2;
        this.handlerReceiveLocation.run(bdLocation,this.tickTimes);

    }
}
