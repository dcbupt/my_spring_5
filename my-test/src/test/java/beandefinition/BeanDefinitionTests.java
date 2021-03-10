package beandefinition;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.tests.sample.beans.mine.TestA;
import org.springframework.tests.sample.beans.mine.TestB;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BeanDefinitionTests {

	/**
	 * getBean方法可以传入别名，但getBeanDefinition方法只能传入beanName，不认别名
	 */
	@Test
	public void testBeanName() {
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"beandefinition/BeanDefinitionTest.xml"));
		assertNotNull(bf.getBean("beandefinition.TestBean2"));
		assertNotNull(bf.getBeanDefinition("beandefinition.TestBean2#0"));
	}

	/**
	 * 测试xml里的<property>标签属性注入到BD
	 */
	@Test
	public void testPropertyValuesInBD() {
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"beandefinition/BeanDefinitionTest.xml"));
		BeanDefinition bd = bf.getBeanDefinition("beandefinition.TestBean2#0");
		assertNotNull(bd);
		List<PropertyValue> propertyValues = bd.getPropertyValues().getPropertyValueList();
		assertFalse(CollectionUtils.isEmpty(propertyValues));
		assertEquals("custom", ((TypedStringValue)bd.getPropertyValues().get("name")).getValue());
		assertEquals("25", ((TypedStringValue)bd.getPropertyValues().get("age")).getValue());
	}

	/**
	 * 测试类定义的元属性
	 */
	@Test
	public void testMeta() {
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"beandefinition/BeanDefinitionTest.xml"));
		BeanDefinition bd = bf.getBeanDefinition("beandefinition.TestBean2#0");
		assertNotNull((String)bd.getAttribute("123"), "456");
		assertNotNull((String)bd.getAttribute("abc"), "def");
	}

	/**
	 * 测试<bean profile="xxx">按环境选择性注册BD
	 * 有activeProfiles，不满足则无法注册beans。没有activeProfiles，再去判断defaultProfiles是否满足。Spring有默认defaultProfile=default
	 */
	@Test(expected = NoSuchBeanDefinitionException.class)
	public void testProfile() {
		//System.setProperty("spring.profiles.active", "daily,pre,online");
		//System.setProperty("spring.profiles.default", "test");
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"beandefinition/BeanDefinitionTest.xml"));
		// 获取BD，不存在抛出NoSuchBeanDefinitionException异常
		bf.getBeanDefinition("testBean");
	}

}
