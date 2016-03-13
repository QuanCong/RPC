package rpc.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ConnectionContext {
	private static Logger logger=Logger.getLogger(ConnectionContext.class);
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private String serviceName;
	private String methodName;
	private Class<?>[] parameterTypes;
	private Object[] arguments;
	public  ConnectionContext(Socket socket)
	{
		this.socket=socket;
		try {
			this.inputStream=new ObjectInputStream(socket.getInputStream());
			this.outputStream=new ObjectOutputStream(socket.getOutputStream());
			parseContext();
		} catch (Exception e) {
			
			logger.error("InputStream参数解析出错");
		}
		
		
		
	}
	private void parseContext() throws IOException, ClassNotFoundException
	{
		this.methodName=inputStream.readUTF();
		this.parameterTypes=(Class<?>[]) inputStream.readObject();
		this.arguments=(Object[]) inputStream.readObject();
	}
	public String getMethodName() {
		return methodName;
	}
	public void sendResult(Object value) 
	{
		try {
			this.outputStream.writeObject(value);
		} catch (IOException e) {
			logger.error("OutputStream 发送出错");
		}
	}
	
	public void close()
	{
		try {
			if(null!=inputStream)
			{
				inputStream.close();
			}
			if(null!=outputStream)
			{
				outputStream.close();
			}
			socket.close();
		} catch (IOException e) {
			
			logger.error("socket 流关闭出错");
		}
		
	}
	public Object[] getArguments() {
		return arguments;
	}
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
	public String getServiceName() {
		return serviceName;
	}
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}
	

	

}
