package instantiation.before.lookup;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote lookup注入测试
 */
public class LookupTest {

	/**
	 * @apiNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean
	 * 使用lookup注入方式的bean，配置的override方法信息会封装并保存到bd.methodOverrides，在bean实例化前会校验override方法是否存在
	 *
	 * @apiNote see org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy.LookupOverrideMethodInterceptor#intercept
	 * 使用lookup注入方式的bean，会被CgLIB代理
	 * 拦截方法里从bd.methodOverrides获取override方法返回的beanName，加载bean作为方法的返回值
	 */
	@Test
	@SuppressWarnings("resource")
	public void lookupTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/lookup/LookupTest.xml");
		CoffeeLover coffeeLover = (CoffeeLover) ctx.getBean("coffeeLover");
		assertNotNull(coffeeLover);
		assertEquals(coffeeLover.getCoffeeBrand(), "LuckIn");
	}

}
