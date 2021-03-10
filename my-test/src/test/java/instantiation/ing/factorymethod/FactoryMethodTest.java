package instantiation.ing.factorymethod;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @implNote 工厂方法实例化bean测试
 * @implNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance
 */
public class FactoryMethodTest {

	/**
	 * @apiNote xml方式，配置factory-method
	 * 只会创建工厂beanDefinition，bd.factoryMethodName=工厂方法
	 * 加载工厂bean的实例化阶段，反射调用工厂方法实例化真正要加载的bean
	 */
	@Test
	public void xmlFactoryMethodTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/ing/factorymethod/FactoryMethodTest.xml");
		People People = (People) ctx.getBean("people");
		assertNotNull(People);
		assertEquals(People.getName(), "dc");
	}

	/**
	 * @apiNote @Bean注解方式，factory-method为@Bean修饰的方法
	 * 会创建两个beanDefinition，分别是配置类BeanDefinition和@Bean方法得到的beanDefinition，后者的beanName=方法名，bd.factoryMethodName=@Bean修饰的方法，bd.factoryBeanName=配置类beanName
	 */
	@Test
	public void annotationFactoryMethodTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationPeopleFactory.class);
		People People = (People) ctx.getBean("people");
		assertNotNull(People);
		assertEquals(People.getName(), "dc");
	}

	/**
	 * factory-method方式注册的bean，注入到其他bean时，byType和byName都可以
	 * 如果是xml方式，byName为工厂bean的beanName，byType为工厂方法返回的类型
	 */
	@Test
	public void factoryMethodBeanInjectTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/ing/factorymethod/FactoryMethodTest.xml");
		instantiation.ing.factorymethod.Test test = (instantiation.ing.factorymethod.Test) ctx.getBean("test");
		assertNotNull(test);
		// byType时不为null，注入的两个people都是同一个people单例
		assertNull(test.getPeople1());
		assertEquals(test.getPeople().getName(), "dc");
	}

}
