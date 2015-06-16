package weChat.service.weixin;

import weChat.core.metatype.Dto;
import weChat.domain.primary.Wechatpubinfo;
import weChat.parameter.IRespParam;
import weChat.parameter.weixin.WReqParam;

public interface WeixinInfoService {

	/**
	 * 获取微信access_token和jsapi_ticket
	 * 
	 * @param param
	 * @return
	 */
	public IRespParam getAccessToken(Wechatpubinfo wechatpubinfo)  throws Exception;

	/**
	 * 通过Https协议向微信接口请求access_token
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	public Dto getAccessToken(String appid, String secret) throws Exception;

	/**
	 * 通过https协议向微信请求请求jsapi_ticket
	 * 
	 * @param accessToken
	 * @return
	 */
	public Dto getApiTicket(String accessToken) throws Exception;
}
