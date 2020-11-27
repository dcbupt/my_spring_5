package instantiation.ing.factorymethod;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote 工厂方法实例化bean测试
 * @implNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance
 */
public class FactoryMethodTest {

	/**
	 * @apiNote xml方式，配置factory-method
	 */
	@Test
	@SuppressWarnings("resource")
	public void xmlFactoryMethodTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/ing/factorymethod/FactoryMethodTest.xml");
		People People = (People) ctx.getBean("people");
		assertNotNull(People);
		assertEquals(People.getName(), "dc");
	}

	/**
	 * @apiNote @Bean注解方式，factory-method为@Bean修饰的方法
	 */
	@Test
	@SuppressWarnings("resource")
	public void annotationFactoryMethodTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationPeopleFactory.class);
		People People = (People) ctx.getBean("people");
		assertNotNull(People);
		assertEquals(People.getName(), "dc");
	}

}
