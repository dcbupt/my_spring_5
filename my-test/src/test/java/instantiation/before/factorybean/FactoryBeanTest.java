package instantiation.before.factorybean;

import common.MyTestBean;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote FactoryBean方式创建bean测试
 * FactoryBean是一类特殊的bean，getBean时会调用FactoryBean的getObject方法获取实际的bean实例
 * see AbstractBeanFactory#getObjectForBeanInstance
 *
 * 实现了FactoryBean接口的bean称为工厂bean，注册到bean工厂的是工厂bean的BD
 * 加载realBean的两种方式：byFactoryBeanName或者byRealBeanType，推荐byRealBeanType
 * 这两种方式最终都是byFactoryBeanName加载，调用工厂bean的getObject方法返回realBean并缓存。realBean不参与bean后续的加载流程，包括依赖注入和初始化
 * 如果想加载工厂bean，使用byName方式，name为'&factoryBeanName'
 */
public class FactoryBeanTest {

	@Test
	public void factoryBeanTest() throws Exception {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/factorybean/FactoryBeanTest.xml");
		// 如果想访问工厂bean，beanName前加'&'
		MyFactoryBean myFactoryBean = (MyFactoryBean) ctx.getBean("&myFactoryBean");
		assertNotNull(myFactoryBean);
		// 加载工厂bean创建的bean实例，可以byFactoryBeanName，也可以byRealBeanType。最终都是byFactoryBeanName加载，调用工厂bean的getObject方法返回realBean并缓存
		MyTestBean myTestBean = (MyTestBean) ctx.getBean("myFactoryBean");
		MyTestBean myTestBean1 = ctx.getBean(MyTestBean.class);
		assertNotNull(myTestBean);
		assertNotNull(myTestBean1);
		assertEquals(myTestBean, myTestBean1);
		// 因为Spring缓存了工厂bean创建的realBean，所以外部调用工厂bean的getObject返回的必然不是SpringIoC管理的realBean
		assertNotEquals(myFactoryBean.getObject(), myTestBean);
		assertEquals(myTestBean.getName(), "dc");
		assertEquals(myTestBean1.getName(), "dc");
	}

	@Test
	public void factoryBeanInjectTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/factorybean/FactoryBeanTest.xml");
		instantiation.before.factorybean.Test myTestBean = (instantiation.before.factorybean.Test) ctx.getBean("test");
		assertNotNull(myTestBean);
		assertEquals(myTestBean.getMyTestBean().getName(), "dc");
	}

}
