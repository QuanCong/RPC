package rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import rpc.service.RpcService;

public class RpcClient {
	
	public  RpcService referService(Class interfaceClass,final String ip,final int port)
	{
		return (RpcService )Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
			
			@Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				
				Socket socket=new Socket(ip,port);
				
				ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
				objectOutputStream.writeUTF(method.getName());
				objectOutputStream.writeObject(method.getParameterTypes());
				objectOutputStream.writeObject(args);
				
				ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
				Object vaObject=objectInputStream.readObject();
				
				objectInputStream.close();
				objectInputStream.close();
				socket.close();
				return vaObject;
			}
		});
	}
	
	public static void main(String[] args) {
		
		RpcClient rpcClient=new RpcClient();
		RpcService<?> service=rpcClient.referService(RpcService.class, "127.0.0.1",7022);
		System.out.println(service.doService(new Object[]{}));
		System.out.println(service.doService("songxu"));
		
		
		
	}

}
