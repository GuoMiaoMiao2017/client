package com.xiongzehua.client.RpcFramework;

public class RpcRedisClient {

    private Redis service;

    public RpcRedisClient(String ip, int port) throws Exception {
        service = RpcConsumer.refer(Redis.class, ip, port);
    }

    public void set(String key, String value) {
        service.set(key, value);
    }
}
