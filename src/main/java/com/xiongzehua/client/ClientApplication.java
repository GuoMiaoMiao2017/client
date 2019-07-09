package com.xiongzehua.client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gmm
 * @ description
 * @ date create in 2019年6月25日14:55:03
 */
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
//        Redis service = RpcConsumer.refer(Redis.class, "127.0.0.1", 1234);
//        service.set("第一个key", "第一个value");
//        String value = service.get("第一个key");
//        System.out.println("客户端收到RPC的结果 ： " + value);
    }
}

