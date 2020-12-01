package initialization.ing.afterpropset;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote afterPropertiesSet测试
 */
public class AfterPropSetTest {

	@Test
	@SuppressWarnings("resource")
	public void afterPropSetTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"initialization/ing/afterpropset/AfterPropSetTest.xml");
		AfterPropSetBean afterPropSetBean = (AfterPropSetBean) ctx.getBean("afterPropSetBean");
		assertNotNull(afterPropSetBean);
		assertEquals(afterPropSetBean.getFollowedArg(), "after_prop_set_configArg");
	}

}
