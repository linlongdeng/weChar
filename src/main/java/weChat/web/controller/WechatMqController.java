package weChat.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.parameter.IRespParam;
import weChat.parameter.impl.RReqParam;
import weChat.service.WechatMqService;
import static weChat.utils.AppConstants.*;

@RestController
@RequestMapping("/RabbitmqRpc")
public class WechatMqController {
	@Autowired
	private BeanFactory beanFactory;

	@RequestMapping
	public IRespParam loadAction(@ModelAttribute(WJMQ_CMDID) String cmdid,
			@ModelAttribute(WJMQ_PARAM) RReqParam param) throws Exception {
		String beanName = cmdid + WJMQ_SUFFIX;
		// 动态获取消息
		Object bean = beanFactory.getBean(beanName);
		Assert.notNull(bean, "cmdid参数错误");
		WechatMqService mqService = (WechatMqService) bean;
		return mqService.handle(param);
	}

	@ModelAttribute
	public void populateModel(@RequestBody @Valid RReqParam param, Model model) {
		model.addAttribute(WJMQ_CMDID, param.getCmdid());
		model.addAttribute(WJMQ_PARAM, param);

	}
}
