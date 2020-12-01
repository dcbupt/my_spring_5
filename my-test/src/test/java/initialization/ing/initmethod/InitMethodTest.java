package initialization.ing.initmethod;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InitMethodTest {

	@Test
	@SuppressWarnings("resource")
	public void initMethodTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"initialization/ing/initmethod/InitMethodTest.xml");
		InitMethodBean initMethodBean = (InitMethodBean) ctx.getBean("initMethodBean");
		assertNotNull(initMethodBean);
		assertEquals(initMethodBean.getInitArg(), "inited");
	}
}
