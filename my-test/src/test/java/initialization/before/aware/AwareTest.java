package initialization.before.aware;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote aware自省注入测试
 */
public class AwareTest {

	@Test
	@SuppressWarnings("resource")
	public void awareTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"initialization/before/aware/AwareTest.xml");
		AwareEnableBean awareEnableBean = ctx.getBean(AwareEnableBean.class);
		assertNotNull(awareEnableBean);
		assertNotNull(awareEnableBean.getBeanFactory());
		assertNotNull(awareEnableBean.getBeanName());
		assertNotNull(awareEnableBean.getClassLoader());
		assertEquals(awareEnableBean.getBeanName(), "initialization.before.aware.AwareEnableBean#0");
	}

}
