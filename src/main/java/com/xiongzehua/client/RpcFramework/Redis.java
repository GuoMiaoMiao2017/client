package com.xiongzehua.client.RpcFramework;

public interface Redis {
    void set(String key, String value);
    String get(String key);
}
