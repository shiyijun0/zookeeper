package com.example.zkconfig.listener;


import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by VULCAN on 2018/7/28.
 */

@Component
public class InitListener implements ServletContextListener {
    private static final String URL="db/url";
    private static final  String PASSWORD="db/password";
    private static final String USERNAME="db/username";
    private static final  String DRIVER="/db/driver";

    ZkClient zkClient=new ZkClient("39.105.169.182:2182",5000,5000,new MyZkSerializer());
    private String path="/election";//选举

    @Value("${server.port}")
    private int port;


    private void init(){
        System.out.println("项目启动完成,端口号为："+port);
        //创建事件监听
        zkClient.subscribeDataChanges(URL, new IZkDataListener() {

            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                try {
                 //   Object datasource= RuntimeContext

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            //节点被删除   主节点挂了，重新选举
            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });

    }



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
if(zkClient!=null){
    zkClient.close();
}
    }
}
