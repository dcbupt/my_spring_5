package instantiation.ing.constructor;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @implNote 指定构造参数的bean实例化测试
 * @implNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#autowireConstructor
 */
public class ConstructorTest {

	/**
	 * @apiNote beanUseConstructor1,beanUseConstructor2配置了不同构造参数，spring反射获取beanClass的所有构造函数，选择匹配的构造函数实例化
	 * @apiNote beanUseConstructor3配置构造参数时使用SPEL，spring在实例化前，会解析得到SPEL表达式的值
	 * 如果构造参数是一个bean引用，也会加载bean，这里不做测试
	 */
	@Test
	@SuppressWarnings("resource")
	public void constructorTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/ing/constructor/ConstructorTest.xml");
		BeanUseConstructor beanUseConstructor1 = (BeanUseConstructor) ctx.getBean("beanUseConstructor1");
		assertNotNull(beanUseConstructor1);
		assertEquals(beanUseConstructor1.getArg1(), "arg1");
		assertEquals(beanUseConstructor1.getArg2(), "arg2");

		BeanUseConstructor beanUseConstructor2 = (BeanUseConstructor) ctx.getBean("beanUseConstructor2");
		assertNotNull(beanUseConstructor2);
		assertEquals(beanUseConstructor2.getArg1(), "arg1");
		assertNull(beanUseConstructor2.getArg2());

		BeanUseConstructor beanUseConstructor3 = (BeanUseConstructor) ctx.getBean("beanUseConstructor3");
		assertNotNull(beanUseConstructor3);
		assertEquals(beanUseConstructor3.getArg1(), "arg1");
		assertNull(beanUseConstructor3.getArg2());
	}

}
