package weChat.service.weixin.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.core.utils.CommonUtils;
import weChat.core.utils.HttpClientUtils;
import weChat.domain.primary.Wechatpubinfo;
import weChat.parameter.IRespParam;
import weChat.parameter.common.DynamicRespParam;
import weChat.parameter.weixin.WReqParam;
import weChat.service.weixin.WeixinInfoService;
import weChat.utils.AppUtils;
import weChat.utils.RespUtils;
import static weChat.utils.AppConstants.*;

@Service
@ConfigurationProperties(prefix = WeixinInfoServiceImpl.WEIXIN_PREFIX)
public class WeixinInfoServiceImpl implements WeixinInfoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String WEIXIN_PREFIX = "weixin";
	/** 微信的access_token **/
	private String accessTokenURI;
	/** 微信的jsapi_ticket **/
	private String jsTicketUR;

	@Override
	public IRespParam getAccessToken(Wechatpubinfo wechatpubinfo)
			throws Exception {
		String appID = wechatpubinfo.getAppID();
		String appSecret = wechatpubinfo.getAppSecret();
		// 获取accessToken
		Dto rDto = getAccessToken(appID, appSecret);
		DynamicRespParam respParam = new DynamicRespParam();
		// 先判断接口调用是否成功
		if (AppUtils.checkWeixinApi(rDto)) {
			String access_token = rDto.getAsString("access_token");
			respParam.set("accesstoken", access_token);
			// 获取ApiTicket
			rDto = getApiTicket(access_token);
			// 判断接口调用是否成功
			if (AppUtils.checkWeixinApi(rDto)) {
				String ticket = rDto.getAsString("ticket");
				respParam.set("ticket", ticket);
				return respParam;
			}
		}
		// 剩下的就是调用出错
		Integer ret = rDto.getAsInteger(WEIXIN_ERRCODE);
		String msg = rDto.getAsString(WEXIN_ERRMSG);
		logger.error("调用微信接口失败，错误码:{},错误消息:{}", ret, msg);
		return RespUtils.errorMR(ret, msg);
	}

	public String getAccessTokenURI() {
		return accessTokenURI;
	}

	public void setAccessTokenURI(String accessTokenURI) {
		this.accessTokenURI = accessTokenURI;
	}

	public String getJsTicketUR() {
		return jsTicketUR;
	}

	public void setJsTicketUR(String jsTicketUR) {
		this.jsTicketUR = jsTicketUR;
	}

	@Override
	public Dto getAccessToken(String appid, String secret) throws Exception {
		BaseDto pDto = new BaseDto();
		pDto.put("grant_type", "client_credential");
		pDto.put("appid", appid);
		pDto.put("secret", secret);
		BaseDto rDto = HttpClientUtils.get(accessTokenURI, pDto, BaseDto.class,
				HttpClientUtils.getSimleRequestConfig());
		return rDto;
	}

	@Override
	public Dto getApiTicket(String accessToken) throws Exception {

		BaseDto pDto = new BaseDto();
		pDto.put("access_token", accessToken);
		pDto.put("type", "jsapi");
		BaseDto rDto = HttpClientUtils.get(jsTicketUR, pDto, BaseDto.class,
				HttpClientUtils.getSimleRequestConfig());
		return rDto;
	}

}
