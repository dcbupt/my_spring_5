package initialization.before.process;

import common.MyTestBean;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote bean前置初始化处理测试
 */
public class BeforeInitializationProcessTest {

	@Test
	@SuppressWarnings("resource")
	public void awareTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"initialization/before/process/BeforeInitializationProcessTest.xml");
		MyTestBean myTestBean = (MyTestBean) ctx.getBean("myTestBean");
		assertNotNull(myTestBean);
		assertEquals(myTestBean.getName(), "beforeInitializeProcessedBean");
	}

}
