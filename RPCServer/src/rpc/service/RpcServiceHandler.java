package rpc.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import rpc.network.ConnectionContext;

public class RpcServiceHandler implements Runnable {

	private ConnectionContext context;
	private RpcService<?> service;
	private static HashMap<String,Class> cachedClass=new HashMap<>();
	private static HashMap<String ,Method> cachedMethod=new HashMap<>();
	
	public RpcServiceHandler(ConnectionContext context,RpcService<?> service)
	{
		this.context=context;
		this.service=service;
	}
	
	
	@Override public void run() {
		try {
			
			Method method;
			//增加反射缓存
			
			if(cachedMethod.containsKey(context.getMethodName())){
				method=cachedMethod.get(context.getMethodName());
			}else {
				method=method=service.getClass().getDeclaredMethod(context.getMethodName(), context.getParameterTypes());
				cachedMethod.put(context.getMethodName(), method);
			}
			Object result=method.invoke(service, context.getArguments());
			context.sendResult(result);
			context.close();
			
			
		} catch (Exception e ) {
			
		}
		
	}
	

}
