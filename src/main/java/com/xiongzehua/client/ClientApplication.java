package com.xiongzehua.client;

import com.xiongzehua.client.RpcFramework.RpcConsumer;
import com.xiongzehua.client.service.Redis;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gmm
 * @ description
 * @ date create in 2019年6月25日14:55:03
 */
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) throws Exception {
        Redis service = RpcConsumer.refer(Redis.class, "127.0.0.1", 1237);
        service.set("第个key", "第个value");
        String value = service.get("第个key");
        System.out.println("客户端收到RPC的结果 ： " + value);
    }
}

