package populatebean.spel;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote SPEL表达式注入测试
 */
public class SpelTest {

	/**
	 * @apiNote xml方式使用SPEL
	 * @apiNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyPropertyValues
	 */
	@Test
	@SuppressWarnings("resource")
	public void xmlSpelTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("populatebean/spel/SpelTest.xml");
		SpelTestBean spelTestBean = (SpelTestBean) ctx.getBean("spelTestBean");
		assertNotNull(spelTestBean);
		assertEquals(spelTestBean.getSpelUseName(), "dc");
	}

	/**
	 * @apiNote @Value注解方式使用SPEL
	 * @apiNote see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor#postProcessPropertyValues
	 */
	@Test
	@SuppressWarnings("resource")
	public void annotationSpelTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfigForSpel.class);
		SpelTestComponent spelTestComponent = (SpelTestComponent) ctx.getBean("spelTestComponent");
		assertNotNull(spelTestComponent);
		assertEquals(spelTestComponent.getSpelUseName(), "dc");
	}

}
