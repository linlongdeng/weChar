package weChat.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import weChat.core.metatype.Dto;
import weChat.domain.primary.Wechatpubinfo;
import weChat.parameter.IRespParam;
import weChat.parameter.impl.WReqParam;
import weChat.service.WeixinInfoService;

@Service
@ConfigurationProperties(prefix = WeixinInfoServiceImpl.WEIXIN_PREFIX)
public class WeixinInfoServiceImpl implements WeixinInfoService {

	public static final String WEIXIN_PREFIX = "weixin";
	/** 微信的access_token **/
	private String accessTokenURI;
	/** 微信的jsapi_ticket **/
	private String jsTicketUR;

	@Override
	public IRespParam getAccessToken(Wechatpubinfo wechatpubinfo) {

		return null;
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
	public Dto getAccessToken(String appid, String secret) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dto getApiTicket(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
