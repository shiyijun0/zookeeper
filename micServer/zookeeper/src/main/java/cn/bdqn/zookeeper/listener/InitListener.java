package cn.bdqn.zookeeper.listener;


import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by VULCAN on 2018/7/28.
 */

@Component
public class InitListener implements ServletContextListener {
    ZkClient zkClient=new ZkClient("39.105.169.182:2182");
    private String path="/election";//选举

    @Value("${server.port}")
    private int port;

    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            createEphemeral();
        }
    };


    Runnable runnable=()->{
        System.out.println("********lamda**"+Thread.currentThread().getName());
    };


    private void init(){
        System.out.println("项目启动完成,节点为："+port);
        createEphemeral();
        //创建事件监听
        zkClient.subscribeDataChanges(path, new IZkDataListener() {

            @Override
            public void handleDataChange(String s, Object o) throws Exception {


            }
            //节点被删除   主节点挂了，重新选举
            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("主节点挂了，重新选举");
                Timer timer=new Timer();
                timer.schedule(timerTask,5000);
            }
        });

    }

    private void createEphemeral(){
        try {
            zkClient.createEphemeral(path,port);//创造临时的领导者
            ElectionMaster.isSurvival=true;
            System.out.println("主节点选中成功"+port);
        }catch (Exception e){
            ElectionMaster.isSurvival=false;
        }


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
