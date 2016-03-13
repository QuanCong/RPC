package rpc.server;

import rpc.service.HelloServiceImpl;
import rpc.service.RpcService;

public class Main {
	public static void main(String[] args) {
		
		RpcService service=new HelloServiceImpl();
		RpcServer rpcServer=new RpcServerImpl();
		
		rpcServer.exportServer(service, 7022);
		
	}

}
