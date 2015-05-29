package weChat.parameter.impl;

import java.util.List;

import weChat.core.metatype.BaseDto;

public class KRespResParam {
	private int ret;

	private String msg;
	
	private List<BaseDto> res;

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

	public List<BaseDto> getRes() {
		return res;
	}

	public void setRes(List<BaseDto> res) {
		this.res = res;
	}
	
}
