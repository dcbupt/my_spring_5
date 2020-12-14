package instantiation.before.factorybean;

import common.MyTestBean;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<MyTestBean> {

	//创建的具体bean对象的类型
	@Override
	public Class<?> getObjectType() {
		return MyTestBean.class;
	}


	//是否单例
	@Override
	public boolean isSingleton() {
		return true;
	}

	//工厂bean 具体创建具体对象是由此getObject()方法来返回的
	@Override
	public MyTestBean getObject() throws Exception {
		MyTestBean myTestBean = new MyTestBean();
		myTestBean.setName("dc");
		return myTestBean;
	}

}
