package org.springframework.mine;

import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.tests.sample.beans.TestBean;
import org.springframework.tests.sample.beans.mine.TestA;
import org.springframework.tests.sample.beans.mine.TestB;

import static org.junit.Assert.assertEquals;

public class BeanFactoryTests {

	// 测试xml定义的bean加载
	@Test
	public void testXmlBeanFactory() {
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("org/springframework/beans/mine/BeanFactoryTest.xml"));
		TestBean tb = (TestBean) bf.getBean("testBean");
		System.out.println(tb.getName());
		assertEquals("custom", tb.getName());
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
