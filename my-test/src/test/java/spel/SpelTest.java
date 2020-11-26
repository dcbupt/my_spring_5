package spel;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpelTest {

	/**
	 * @apiNote see AbstractAutowireCapableBeanFactory#applyPropertyValues
	 */
	@Test
	@SuppressWarnings("resource")
	public void spelTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spel/SpelTest.xml");
		SpelTestBean spelTestBean = (SpelTestBean) ctx.getBean("spelTestBean");
		assertNotNull(spelTestBean);
		assertEquals(spelTestBean.getSpelUseName(), "dc");
	}

}
