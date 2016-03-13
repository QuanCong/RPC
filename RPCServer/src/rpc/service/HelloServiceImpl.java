package rpc.service;

import org.apache.log4j.Logger;

public class HelloServiceImpl  implements RpcService<String>{

	private static Logger logger=Logger.getLogger(HelloServiceImpl.class);
	@Override public String doService(Object... params) {

		logger.info("service work! params:"+params);
		if(params==null||params.length==0)	{
			return "please give me a value";
		}
		return "hello"+params[0]+"welcome use RPC Framework";
		
		
		
		
	}
}
