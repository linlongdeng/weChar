package weChat.parameter.impl;

import weChat.core.metatype.BaseDto;
import weChat.parameter.IRespParam;
/**
 * 远程调用协议(微信服务发起) 返回消息
 * @author deng
 * @date 2015年5月25日
 * @version 1.0.0
 */
public class RRespParam implements IRespParam {

	private int ret;

	private String msg;

	private String cmdid;

	private BaseDto data;

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

	public String getCmdid() {
		return cmdid;
	}

	public void setCmdid(String cmdid) {
		this.cmdid = cmdid;
	}

	public BaseDto getData() {
		return data;
	}

	public void setData(BaseDto data) {
		this.data = data;
	}

}
