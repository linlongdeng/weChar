package weChat.core.jpa;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;

@SuppressWarnings("serial")
public class MyNameNameingStrategy extends SpringNamingStrategy {

	@Override
	public String propertyToColumnName(String propertyName) {
		// TODO Auto-generated method stub
		System.err.println("测试成功");
		return super.propertyToColumnName(propertyName);
	}

	@Override
	public String columnName(String columnName) {
		// TODO Auto-generated method stub
		System.err.println("测试成功");
		return super.columnName(columnName);
	}
	
	

}
