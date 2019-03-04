package com.azeroth.utility;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

import static com.alibaba.fastjson.parser.Feature.CustomMapDeserializer;

public class RT<T>
{
    @JSONField(deserializeUsing=RTCodeDeserializer.class,serializeUsing = RTCodeDeserializer.class,parseFeatures = Feature.CustomMapDeserializer)
    public RTCode codej;

    public String msg;
    public T data;
    public Object extension;

    public enum RTCode
    {
        Ok(1),
        Error(2),
        NoAuthentication(3),
        NoAuthorization(4);
        int value;
        private RTCode(int v){
            value=v;
        }
    }

    public static class RTCodeDeserializer implements ObjectDeserializer,ObjectSerializer {
        @Override
        public  <M> M deserialze(DefaultJSONParser parser, Type type, Object fieldName){
            Object value = parser.parse();
            if(value==null)
                return  null;
            RT.RTCode[] enums = RT.RTCode.values();
            int tmp=(int)value;
            for(RT.RTCode e : enums){
                if(e.value == tmp)
                    return (M)e;
            }
            throw  new RuntimeException("反序列化RTCode,遇到未识别值");
        }
        @Override
        public int getFastMatchToken(){
            return 0;
        }
        @Override
        public void write(JSONSerializer serializer, //
                          Object object, //
                          Object fieldName, //
                          Type fieldType, //
                          int features) throws IOException
        {
            Integer a=3;
        }
    }
}


