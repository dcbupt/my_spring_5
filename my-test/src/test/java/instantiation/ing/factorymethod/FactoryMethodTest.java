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
 *
 * factory-method有两种方式：通过xml配置的方式指定，或者通过@Bean方式指定
 * xml方式和FactoryBean方式基本一致，区别是factory-method所在类不会成为bean，加载realBean时，通过反射调用工厂类的factory-method方法实例化bean，并参与后续bean依赖注入和初始化流程
 * "@Bean"方式会为配置类和@Bean方法都注册beanDefinition，因此要加载@Bean方式注册的bean和普通bean无区别，本质会得到配置类bean的class类，反射调用factory-method方法（@Bean修饰的方法）实例化realBean，并参与后续bean依赖注入和初始化流程
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
		People people = (People) ctx.getBean("peopleFactory");
		People people1 = (People) ctx.getBean(People.class);
		assertNotNull(people);
		assertNotNull(people1);
		assertEquals(people, people1);
		assertEquals(people.getName(), "dc");
		assertEquals(people1.getName(), "dc");
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
	 * factory-method方式注册的bean
	 * 如果是xml方式，byFactoryBeanName或者byRealBeanType。@Bean方式创建的就是普通bean，直接byRealBeanName或byRealBeanType即可
	 */
	@Test
	public void factoryMethodBeanInjectTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/ing/factorymethod/FactoryMethodTest.xml");
		instantiation.ing.factorymethod.Test test = (instantiation.ing.factorymethod.Test) ctx.getBean("test");
		assertNotNull(test);
		// byType时不为null，注入的两个people都是同一个people单例
		assertNull(test.getPeople1());
		assertEquals(test.getPeopleFactory().getName(), "dc");
	}

}
