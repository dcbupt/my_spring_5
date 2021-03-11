package instantiation.before.factorybean;

import common.MyTestBean;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote FactoryBean方式创建bean测试
 * FactoryBean是一类特殊的bean，getBean时会调用FactoryBean的getObject方法获取实际的bean实例
 * see AbstractBeanFactory#getObjectForBeanInstance
 */
public class FactoryBeanTest {

	/**
	 * 工厂bean返回的真正bean实例，可以通过byType方式加载或注入，本质上会按类型找到工厂bean的beanName，再加载工厂bean，调用getObject返回真正的bean实例
	 * 但如果是byName，只能是工厂bean的beanName，因为真正加载的bean不会注册它的BD。所以如果要注入工厂bean创建的bean实例，推荐byType
	 */
	@Test
	public void factoryBeanTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/factorybean/FactoryBeanTest.xml");
		MyTestBean myTestBean = (MyTestBean) ctx.getBean("myFactoryBean");
		MyTestBean myTestBean1 = ctx.getBean(MyTestBean.class);
		assertNotNull(myTestBean);
		assertNotNull(myTestBean1);
		assertEquals(myTestBean, myTestBean1);
		assertEquals(myTestBean.getName(), "dc");
		assertEquals(myTestBean1.getName(), "dc");
	}

	/**
	 * 工厂bean返回的真正bean实例，可以通过byType方式加载或注入，本质上会按类型找到工厂bean的beanName，再加载工厂bean，调用getObject返回真正的bean实例
	 * 但如果是byName，只能是工厂bean的beanName，因为真正加载的bean不会注册它的BD。所以如果要注入工厂bean创建的bean实例，推荐byType
	 */
	@Test
	public void factoryBeanInjectTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/factorybean/FactoryBeanTest.xml");
		instantiation.before.factorybean.Test myTestBean = (instantiation.before.factorybean.Test) ctx.getBean("test");
		assertNotNull(myTestBean);
		assertEquals(myTestBean.getMyTestBean().getName(), "dc");
	}

}
