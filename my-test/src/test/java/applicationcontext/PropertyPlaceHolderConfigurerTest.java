package applicationcontext;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class PropertyPlaceHolderConfigurerTest {

	/**
	 * 使用标签"context:property-placeholder"，指定系统配置文件路径。Spring解析标签，注册PPC：PropertySourcesPlaceholderConfigurer（它是bfpp）
	 * spring容器refresh初始化的bean工厂后置处理阶段，回调PPC，将BD.pv里定义的占位符替换为系统配置文件里的实际值
	 */
	@Test
	public void testPPC() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"applicationcontext/PropertyPlaceHolderConfigurerTest.xml");
		PPCTest ppcTest = (PPCTest)ctx.getBean("pPCTest");
		assertEquals(ppcTest.getAbc(), "def");
	}

}
