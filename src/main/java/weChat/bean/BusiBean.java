package weChat.bean;

public class BusiBean<T> {

	
	private String cmd;
	
	
	private T api_params;


	public String getCmd() {
		return cmd;
	}


	public void setCmd(String cmd) {
		this.cmd = cmd;
	}


	public T getApi_params() {
		return api_params;
	}


	public void setApi_params(T api_params) {
		this.api_params = api_params;
	}
}
