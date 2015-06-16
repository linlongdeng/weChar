package weChat.core.exception;

public class ArgumentEmptyException extends RuntimeException {

	private String param;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArgumentEmptyException(){
		param ="未知参数";
	}
	
	public ArgumentEmptyException(String param){
		this.param = param;
		
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
