package weChat.service.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weChat.parameter.manage.MRequestParam;
import weChat.parameter.manage.MResponseParam;
import weChat.repository.GradecollectRepository;
import weChat.service.GradecollectService;
import weChat.utils.ResponseUtils;

@Service
public class GradecollectServiceImpl implements GradecollectService {
	
	@Autowired
	private GradecollectRepository gradecollectRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public MResponseParam syncGrade(MRequestParam param) {
		return ResponseUtils.successMR();
	}

}
