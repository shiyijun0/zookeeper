package cn.bdqn.zookeeper.controller;

import cn.bdqn.zookeeper.listener.ElectionMaster;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by VULCAN on 2018/7/28.
 */

@RestController
public class IndexController {

    @RequestMapping("/getServerInfo")
    public String getServerInfo() {
        return ElectionMaster.isSurvival?"当前服务主节点":"当前服务从节点";
    }
}