package initialization.before.process;

import common.MyTestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyInitializationBeforeProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean.getClass() == MyTestBean.class || "myTestBean".equals(beanName)) {
			MyTestBean myTestBean = (MyTestBean) bean;
			myTestBean.setName("beforeInitializeProcessedBean");
			return myTestBean;
		} else {
			return bean;
		}
	}
}
