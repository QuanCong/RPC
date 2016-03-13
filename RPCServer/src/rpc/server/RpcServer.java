package rpc.server;

import rpc.service.RpcService;

public interface RpcServer {
	
	public void exportServer(RpcService<?> service,int port);
	
	public void startServer();
	
	public void stopServer();
	
}
