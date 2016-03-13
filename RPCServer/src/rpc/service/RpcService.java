package rpc.service;

public interface RpcService<T> {
	
	public T doService(Object ...params);

}
