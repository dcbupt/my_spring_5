package org.springframework.mine;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.tests.sample.beans.TestBean;

import static org.junit.Assert.assertEquals;

public class ApplicationContextTest {

	@Test
	public void testApplicationContext() {
		BeanFactory bf = new ClassPathXmlApplicationContext("org/springframework/beans/mine/BeanFactoryTest.xml");
		TestBean tb = (TestBean) bf.getBean("testBean");
		System.out.println(tb.getName());
		assertEquals("custom", tb.getName());
	}

}
