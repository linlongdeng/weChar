package weChat.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.core.rabbit.RabbitClient;
import weChat.core.rabbit.RabbitClientConfig;
import weChat.parameter.IRespParam;
import weChat.parameter.impl.RReqParam;
import weChat.parameter.impl.RRespParam;
import weChat.service.ValidationService;
import weChat.service.WechatMqService;
import weChat.utils.AppConstants;
import static weChat.core.utils.CommonUtils.*;
import static weChat.utils.AppConstants.*;

@Service("WJ007" + AppConstants.WJMQ_SUFFIX)
public class WechatMqWJ007ServiceImpl extends WechatMqService {

	@Autowired
	public WechatMqWJ007ServiceImpl(RabbitClient rabbitClient,
			RabbitClientConfig config, ValidationService validationService) {
		super(rabbitClient, config, validationService);
	}

	@Override
	public void validate(Object target, Errors e) {
		RReqParam rreqParam = (RReqParam) target;
		BaseDto params = rreqParam.getParams();
		if (isEmpty(params)) {
			e.reject(ARGUMENT_NOT_EMPTY_INFO, new Object[] { "params" }, null);
		}
		else if(isEmpty(params.get("cardnum"))){
			e.reject(ARGUMENT_NOT_EMPTY_INFO, new Object[] { "cardnum" }, null);
		}
	}

}
