package instantiation.before.process;

import common.MyTestBean;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote bean实例化前置处理测试
 */
public class PostProcessBeforeInstantiationTest {

	/**
	 * @apiNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean
	 * @apiNote bean实例化前置处理bpp，直接返回bean实例给beanFactory
	 */
	@Test
	@SuppressWarnings("resource")
	public void postProcessBeforeInstantiationTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"instantiation/before/process/PostProcessBeforeInstantiation.xml");
		MyTestBean myTestBean = (MyTestBean) ctx.getBean("myTestBean");
		assertNotNull(myTestBean);
		assertEquals(myTestBean.getName(), "proxy");
	}
}
