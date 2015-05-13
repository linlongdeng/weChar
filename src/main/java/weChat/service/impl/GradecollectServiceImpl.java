package weChat.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.domain.Company;
import weChat.domain.Gradecollect;
import weChat.parameter.manage.MRequestParam;
import weChat.parameter.manage.MResponseParam;
import weChat.repository.CompanyRepository;
import weChat.repository.GradecollectRepository;
import weChat.service.GradecollectService;
import weChat.utils.RespUtils;

@Service
public class GradecollectServiceImpl implements GradecollectService {

	@Autowired
	private GradecollectRepository gradecollectRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public MResponseParam syncGrade(MRequestParam param) {
		List<BaseDto> data = param.getData();
		String companycode = param.getCompanycode();
		Company company = companyRepository.findFirstByCompanyCode(companycode);
		Assert.notNull(company, "商家编码不存在");
		String wechatPubInfoID = param.getWechatPubInfoID();
		if (data != null) {
			for (BaseDto dto  : data) {
				Integer gradeid = dto.getAsInteger("gradeid");
				 Gradecollect  gradecollect = gradecollectRepository
						.findFirstByCompanyCodeAndWechatPubInfoIDAndGradeID(
								companycode,
								Integer.valueOf(wechatPubInfoID), gradeid);
				String gradecode = dto.getAsString("gradecode");
				String gradename = dto.getAsString("gradename");
				Integer status = dto.getAsInteger("status");
				if (gradecollect == null) {
					gradecollect = new Gradecollect();
					// 有问题
					gradecollect.setCompanyID(company.getCompanyID());
					// 格式不对
					gradecollect.setWechatPubInfoID(Integer
							.valueOf(wechatPubInfoID));
					// 格式不对
					gradecollect.setGradeID(gradeid);
					gradecollect.setCreateTime(new Date());
				}
				gradecollect.setGradeCode(gradecode);
				gradecollect.setGradeName(gradename);
				gradecollect.setStatus(status.byteValue());
				gradecollect.setUpdateTime(new Date());
				gradecollectRepository.save(gradecollect);
			}
		}
		return RespUtils.successMR();
	}
}
