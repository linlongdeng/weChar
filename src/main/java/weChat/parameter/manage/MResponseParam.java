package weChat.parameter.manage;
/**
 *  管理系统数据同步接口（管理系统发起）处理结束参数
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
public class MResponseParam {

	public MResponseParam(int ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
	}

	private int ret;
	
	private String msg;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
