package populatebean.value;

import applicationcontext.PPCTest;
import applicationcontext.configclassparse.ImportedBean;
import applicationcontext.stringvalue.ConfigBean;
import applicationcontext.stringvalue2.ConfigBean2;
import applicationcontext.stringvalue.ValueBean;
import applicationcontext.stringvalue2.ValueBeanUsePpc;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.*;

public class ValueTest {

	/**
	 * bean依赖注入阶段对bd.pv不会做占位符解析
	 * @throws InterruptedException
	 */
	@Test
	public void testXmlPlaceHolder() throws InterruptedException {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"populatebean/value/StringValueResolverTest.xml");
		ValueBean valueBean = ctx.getBean(ValueBean.class);
		Assert.assertEquals(valueBean.getPath(), "${PATH}");
	}

	/**
	 * 使用标签"context:property-placeholder"，(可选)指定系统配置文件路径。Spring解析标签，注册PPC：PropertySourcesPlaceholderConfigurer（它是bfpp）
	 * spring容器refresh初始化的bean工厂后置处理阶段，回调PPC，将bd.pv里定义的占位符替换为实际值
	 * 优先级：
	 * - JVM参数
	 * - 系统环境变量
	 * - 应用配置文件
	 */
	@Test
	public void testPPC() {
		System.setProperty("PATH", "JVMPATH");
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"populatebean/value/PropertyPlaceHolder.xml");
		PPCTest ppcTest = (PPCTest)ctx.getBean("pPCTest");
		assertEquals(ppcTest.getAbc(), "def");
		assertEquals(ppcTest.getPath(), "JVMPATH");
	}

	@Test
	public void testPPC2() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				ConfigBean2.class);
		assertEquals(ctx.getBean(ValueBeanUsePpc.class).getAbc(), "def");
		assertNotNull(ctx.getBean(ImportedBean.class));
	}

	@Test
	public void testAnnotationPlaceHolder() throws InterruptedException {
		System.setProperty("PATH", "JVMPATH");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				ConfigBean.class);
		ValueBean valueBean = ctx.getBean(ValueBean.class);
		assertEquals(valueBean.getPath(), "JVMPATH");
		assertEquals(valueBean.getDataName(), "dc");
	}



}
