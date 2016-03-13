package rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import rpc.network.ConnectionContext;
import rpc.service.RpcService;
import rpc.service.RpcServiceHandler;

public class RpcServerImpl implements RpcServer {
	private static Logger logger=Logger.getLogger(RpcServerImpl.class);
	@Override public void exportServer(RpcService<?> service, int port) {
		ServerSocket serverSocket;
		ExecutorService executorService=Executors.newFixedThreadPool(10);
		try {
			serverSocket = new ServerSocket(port);
			logger.info(service.getClass()+"服务在端口"+port+"注册成功！");
			for(;;)
			{
				Socket socket=serverSocket.accept();
				executorService.submit(new RpcServiceHandler(new ConnectionContext(socket), service));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override public void startServer() {
		// TODO Auto-generated method stub

	}

	@Override public void stopServer() {
		// TODO Auto-generated method stub

	}

}
