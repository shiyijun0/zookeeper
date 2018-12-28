package cn.bdqn.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class, args);
    }

   /* @Bean
    public ServletListenerRegistrationBean servletRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean=new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }*/
}

