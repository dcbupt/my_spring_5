package instantiation.before.process;

import common.MyTestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationBeforeProcessor extends InstantiationAwareBeanPostProcessorAdapter {

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (beanClass == MyTestBean.class || "myTestBean".equals(beanName)) {
			MyTestBean myTestBean = new MyTestBean();
			myTestBean.setName("proxy");
			return myTestBean;
		} else {
			return super.postProcessBeforeInstantiation(beanClass, beanName);
		}
	}
}
