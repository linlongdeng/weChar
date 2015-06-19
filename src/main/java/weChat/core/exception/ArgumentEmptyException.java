package weChat.core.exception;

public class ArgumentEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ret;
	
	private String msg;
	
	private String argument;

	public ArgumentEmptyException(String ret, String msg, String argument) {
		super();
		this.ret = ret;
		this.msg = msg;
		this.argument = argument;
	}

	public ArgumentEmptyException(String ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
		this.argument = null;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

}
