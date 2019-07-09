package com.xiongzehua.client.RpcFramework;

import com.alibaba.fastjson.JSON;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcConsumer {

    public static <T> T refer(Class<T> interfaceClass, final String host, final int port) throws Exception {
        T proxy = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, new InvocationHandler() {                // invoke方法本意是对目标方法的增强，在这里用于发送RPC请求和接收响应
            @Override
            public Object invoke(Object proxy, Method method, Object[] arguments)  throws Throwable {
                Socket socket = new Socket(host, port);
                try {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        System.out.println("客户端发送请求 ： ");
                        output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(arguments);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            String result = JSON.toJSONString(input.readObject());
                            System.out.println("客户端收到响应 ： result = " + result);
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
        return proxy;
    }
}
