package populatebean.spel;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;

/**
 * @implNote SPEL表达式注入测试
 */
public class SpelTest {

	/**
	 * spel的解析器resolver是Spring容器为BeanFactory显示注册的，如果使用BeanFactory，是没有这个resolver的，也就无法解析spel表达式
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
	 * spel的解析器resolver是Spring容器为BeanFactory显示注册的，如果使用BeanFactory，是没有这个resolver的，也就无法解析spel表达式
	 * @apiNote xml方式使用SPEL
	 * @apiNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyPropertyValues
	 */
	@Test
	@SuppressWarnings("resource")
	public void xmlSpelTest2() {
		XmlBeanFactory ctx = new XmlBeanFactory(new ClassPathResource("populatebean/spel/SpelTest.xml"));
		SpelTestBean spelTestBean = (SpelTestBean) ctx.getBean("spelTestBean");
		assertNotNull(spelTestBean);
		assertEquals(spelTestBean.getSpelUseName(), "#{myTestBean.name}");
	}

	/**
	 * 如果是基于Annotation的Spring容器，会默认注册bpp：AutowiredAnnotationBeanPostProcessor，该bpp在bean依赖注入阶段负责@Autowire和@Value的注入
	 * 对于@Value，如果使用占位符，使用StringValueResolver来解析，如果使用spel，使用Spring容器的spel解析器来解析
	 * 如果是基于xml的Spring容器，默认无法解析@Value@Autowire
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
