package org.springframework.mine;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.tests.sample.beans.TestBean;
import org.springframework.tests.sample.beans.mine.TestA;
import org.springframework.tests.sample.beans.mine.TestB;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BeanFactoryTests {

	// 测试xml定义的bean加载
	@Test
	public void testXmlBeanFactory() {
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("org/springframework/beans/mine/BeanFactoryTest.xml"));
		BeanDefinition bd = bf.getBeanDefinition("testBean");
		assertNotNull(bd);
		List<PropertyValue> propertyValues = bd.getPropertyValues().getPropertyValueList();
		assertFalse(CollectionUtils.isEmpty(propertyValues));
		assertEquals("custom", ((TypedStringValue)bd.getPropertyValues().get("name")).getValue());
		assertEquals("25", ((TypedStringValue)bd.getPropertyValues().get("age")).getValue());
	}

	// 测试构造参数循环依赖
	//@Test(expected = BeanCreationException.class)
	@Test
	public void testConstructorCircleDependency() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("org/springframework/beans/mine/BeanFactoryTest.xml"));
		TestA testA = (TestA) bf.getBean("testA");
		TestB testB = (TestB) bf.getBean("testB");
	}

}
