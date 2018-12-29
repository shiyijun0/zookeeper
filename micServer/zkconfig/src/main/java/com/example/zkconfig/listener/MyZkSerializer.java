package com.example.zkconfig.listener;


import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;
//序列化
public class MyZkSerializer implements ZkSerializer {
    @Override
    public byte[] serialize(Object o) throws ZkMarshallingError {
        try {
            return String.valueOf(o).getBytes("utf-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            return new String(bytes,"utf-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }
}
