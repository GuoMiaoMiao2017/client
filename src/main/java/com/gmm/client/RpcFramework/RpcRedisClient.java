package com.gmm.client.RpcFramework;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Service
public class RpcRedisClient {

    private Socket socket;

    public RpcRedisClient() throws Exception {
        socket = new Socket("127.0.0.1", 1235);

    }

    public void set(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "set");
        map.put("parameter", "String," + key + ";String," + value + "\"");
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.write(JSON.toJSONBytes(map));

            System.out.println("发送的map = " + map);

            ObjectInputStream input = null;
            try {

                input = new ObjectInputStream(socket.getInputStream());
                String result = JSON.toJSONString(input.readObject());

                Integer code = JSONObject.parseObject(result).getInteger("code");
                String msg = JSONObject.parseObject(result).getString("msg");

                if (code == 1) {
                    System.out.println("set成功");
                } else {
                    System.out.println("set失败");
                }
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        Map<String, String> map = new HashMap<>();
        map.put("method", "get");
        map.put("parameter", "String," + key);
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.write(JSON.toJSONBytes(map));

            System.out.println("发送的map = " + map);

            ObjectInputStream input = null;
            try {

                input = new ObjectInputStream(socket.getInputStream());
                String result = JSON.toJSONString(input.readObject());

                Integer code = JSONObject.parseObject(result).getInteger("code");
                String msg = JSONObject.parseObject(result).getString("msg");

                if (code == 1) {
                    System.out.println("get成功");
                    System.out.println("得到的值为 msg = " + msg);
                } else {
                    System.out.println("get失败");
                }
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
