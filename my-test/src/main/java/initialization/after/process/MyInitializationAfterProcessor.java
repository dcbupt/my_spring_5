package initialization.after.process;

import common.MyTestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyInitializationAfterProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean.getClass() == MyTestBean.class || "myTestBean".equals(beanName)) {
			MyTestBean myTestBean = (MyTestBean) bean;
			myTestBean.setName("afterInitializeProcessedBean");
			return myTestBean;
		} else {
			return bean;
		}
	}
}
