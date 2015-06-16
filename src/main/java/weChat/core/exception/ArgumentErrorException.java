package weChat.core.exception;

public class ArgumentErrorException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArgumentErrorException(){
		super("参数错误");
	}
}
