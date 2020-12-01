package initialization.after.process;

import common.MyTestBean;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote bean初始化后置处理测试
 */
public class AfterInitializationProcessTest {

	@Test
	@SuppressWarnings("resource")
	public void afterInitializationProcessTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"initialization/after/process/AfterInitializationProcessTest.xml");
		MyTestBean myTestBean = (MyTestBean) ctx.getBean("myTestBean");
		assertNotNull(myTestBean);
		assertEquals(myTestBean.getName(), "afterInitializeProcessedBean");
	}
}
