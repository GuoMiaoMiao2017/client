package com.gmm.client;

import com.gmm.client.RpcFramework.RpcRedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        RpcRedisClient rpcRedisClient = new RpcRedisClient();
        rpcRedisClient.set("啦啦啦", "jijijiji");
    }

}
