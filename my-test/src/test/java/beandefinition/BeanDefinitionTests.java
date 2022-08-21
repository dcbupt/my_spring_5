package beandefinition;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BeanDefinitionTests {

	/**
	 * 通过指定classpath下的xml配置文件来构建Bean工厂
	 * 提供了两种指定xml配置文件的方式
	 */
	@Test
	public void testClassPathResource() {
		/*指定Class，xml文件需要在Class包路径下*/
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"xml/BeanDefinitionTest.xml", TestBean.class));
		assertNotNull(bf.getBean("beandefinition.TestBean2"));
		assertNotNull(bf.getBeanDefinition("beandefinition.TestBean2#0"));

		/*不指定Class时，需要指定xml文件在classpath下的路径*/
		XmlBeanFactory bf1 = new XmlBeanFactory(new ClassPathResource(
				"beanDefinition/BeanDefinitionTest1.xml"));
		assertNotNull(bf1.getBean("beandefinition.TestBean2"));
		assertNotNull(bf1.getBeanDefinition("beandefinition.TestBean2#0"));
	}

	/**
	 * 测试<beans profile="xxx">按profile选择性注册BD
	 * 有activeProfiles，不满足则无法注册beans。没有activeProfiles，再去判断defaultProfiles是否满足。Spring有默认defaultProfile=default
	 */
	@Test(expected = NoSuchBeanDefinitionException.class)
	public void testProfile() {
//		System.setProperty("spring.profiles.active", "daily,pre,online");
//		System.setProperty("spring.profiles.default", "test");
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"beandefinition/BeanDefinitionTest1.xml"));
		// 获取BD，不存在抛出NoSuchBeanDefinitionException异常
		bf.getBeanDefinition("testBean");
	}

	/**
	 * 测试xml 方式注册 BeanDefinition 的前置和后置钩子函数
	 */
	@Test
	public void customDefaultBeanDefinitionDocumentReader() {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		xmlBeanDefinitionReader.setDocumentReaderClass(MyBeanDefinitionDocumentReader.class);
		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("beandefinition/BeanDefinitionTest1.xml"));
		assertNotNull(defaultListableBeanFactory.getBean("beandefinition.TestBean2"));
		assertNotNull(defaultListableBeanFactory.getBeanDefinition("beandefinition.TestBean2#0"));
	}

	/**
	 * 测试bean别名
	 * 如果 id 和 name 都为空，由 spring 生成 BeanName，格式为：{类的全路径名}#0，别名为：类的全路径名
	 */
	@Test
	public void testBeanNameAlias() {
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"beandefinition/BeanDefinitionTest1.xml"));
		assertTrue(bf.hasAlias("beandefinition.TestBean2#0", "testBean2"));
		assertTrue(bf.hasAlias("beandefinition.TestBean2#0", "beandefinition.TestBean2"));
	}

	/**
	 * getBean方法可以传入别名，但getBeanDefinition方法只能传入beanName，不认别名
	 */
	@Test
	public void testBeanName() {
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"beandefinition/BeanDefinitionTest1.xml"));
		assertNotNull(bf.getBean("testBean2"));
		assertNotNull(bf.getBean("beandefinition.TestBean2"));
		assertNotNull(bf.getBeanDefinition("beandefinition.TestBean2#0"));

		BeanDefinition bd1;
		BeanDefinition bd2;
		try {
			bd1 = bf.getBeanDefinition("testBean2");
		} catch (NoSuchBeanDefinitionException e) {
			bd1 = null;
		}

		try {
			bd2 = bf.getBeanDefinition("beandefinition.TestBean2");
		} catch (NoSuchBeanDefinitionException e) {
			bd2 = null;
		}
		assertNull(bd1);
		assertNull(bd2);
	}

	/**
	 * 测试xml里的<property>标签属性注入到BD
	 */
	@Test
	public void testPropertyValuesInBD() {
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(
				"beandefinition/BeanDefinitionTest1.xml"));
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
				"beandefinition/BeanDefinitionTest1.xml"));
		BeanDefinition bd = bf.getBeanDefinition("beandefinition.TestBean2#0");
		assertNotNull((String)bd.getAttribute("123"), "456");
		assertNotNull((String)bd.getAttribute("abc"), "def");
	}



}
