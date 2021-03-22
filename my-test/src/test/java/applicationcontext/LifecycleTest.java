package applicationcontext;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class LifecycleTest {

	/**
	 * Spring容器初始化完成或者close时，都会回调实现SmartLifecycle的bean的start、stop方法
	 * 如果bean还实现了DisposableBean接口，在stop方法后回调destroy方法
	 */
	@Test
	public void testLifecycle() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"applicationcontext/LifecycleTest.xml");
		ctx.close();
	}

}
