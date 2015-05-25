package weChat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weChat.core.metatype.Dto;
import weChat.core.rabbit.RabbitClient;
import weChat.core.rabbit.RabbitClientConfig;
import weChat.parameter.IRespParam;
import weChat.parameter.impl.RReqParam;
import weChat.parameter.impl.RRespParam;
import weChat.service.WechatMqService;
import weChat.utils.AppConstants;
@Service("WJ001" +  AppConstants.WJMQ_SUFFIX)
public class WechatMqWJ001ServiceImpl implements WechatMqService {

	@Autowired
	private RabbitClient rabbitClient;
	@Autowired
	private RabbitClientConfig config;
	@Override
	public void validate(RReqParam param) {

	}

	@Override
	public IRespParam handle(RReqParam param) {
		validate(param);
		String dot = config.getQueuedot();
		String routingKey = "request" + dot + "company" + dot + param.getCompanycode();
		RRespParam resp =  (RRespParam) rabbitClient.convertSendAndReceive(routingKey, param);
		return resp;
	}

}
