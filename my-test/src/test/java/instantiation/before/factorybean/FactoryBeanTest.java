package instantiation.before.factorybean;

import common.MyTestBean;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote FactoryBean方式创建bean测试
 * FactoryBean是一类特殊的bean，getBean时会调用FactoryBean的getObject方法获取实际的bean实例，并加到缓存里。下次直接从缓存取实际bean
 * see AbstractBeanFactory#getObjectForBeanInstance
 */
public class FactoryBeanTest {

	@Test
	@SuppressWarnings("resource")
	public void factoryBeanTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/factorybean/FactoryBeanTest.xml");
		MyTestBean myTestBean = (MyTestBean) ctx.getBean("myTestBean");
		assertNotNull(myTestBean);
		assertEquals(myTestBean.getName(), "dc");
	}

	/**
	 * factoryBean注册的bean，只能通过byName注入到其他bean，byType的话，factoryBean类型也是它实际注册的bean类型
	 */
	@Test
	@SuppressWarnings("resource")
	public void factoryBeanInjectTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/factorybean/FactoryBeanTest.xml");
		instantiation.before.factorybean.Test myTestBean = (instantiation.before.factorybean.Test) ctx.getBean("test");
		assertNotNull(myTestBean);
		assertEquals(myTestBean.getMyTestBean().getName(), "dc");
	}

}
