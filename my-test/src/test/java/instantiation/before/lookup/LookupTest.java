package instantiation.before.lookup;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @implNote lookup注入测试
 */
public class LookupTest {

	/**
	 * @apiNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean
	 * 使用lookup注入方式的bean，配置的override方法信息会封装并保存到bd.methodOverrides，在bean实例化前会校验override方法是否存在
	 *
	 * @apiNote see org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy.LookupOverrideMethodInterceptor#intercept
	 * 配置 lookup-method 的 bean，会被 CgLIB 代理，增强逻辑是从bd.methodOverrides获取lookup-method方法返回的beanName，加载bean作为方法的返回值
	 */
	@Test
	@SuppressWarnings("resource")
	public void lookupTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/lookup/LookupTest.xml");
		CoffeeLover luckInLover = (CoffeeLover) ctx.getBean("luckInLover");
		assertNotNull(luckInLover);
		assertEquals(luckInLover.getCoffeeBrand(), "LuckIn");
		assertNull(luckInLover.getCoffee());

		CoffeeLover starBucksLover = (CoffeeLover) ctx.getBean("starBucksLover");
		assertNotNull(starBucksLover);
		assertEquals(starBucksLover.getCoffeeBrand(), "StarBucks");
		assertNull(starBucksLover.getCoffee());
	}

}
