package applicationcontext;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.MissingRequiredPropertiesException;

public class RequiredPropertyTest {
	/**
	 * 扩展ApplicationContext，重写initPropertySources，可以添加系统变量、设置必须的系统变量
	 */
	@Test(expected = MissingRequiredPropertiesException.class)
	public void testRequiredProperty() {
		MyApplicationContext ctx = new MyApplicationContext(
				"applicationcontext/RequiredPropertyTest.xml");
		System.out.println("app ctx start");

	}

	public static class MyApplicationContext extends GenericXmlApplicationContext {

		public MyApplicationContext(String... s) { super(s);}

		@Override
		protected void initPropertySources() {
			//这里添加了一个name属性到Environment里面，以方便我们在后面用到
			getEnvironment().getSystemProperties().put("name","bobo");
			//这里要求Environment中必须包含username属性，如果不包含，则抛出异常
			getEnvironment().setRequiredProperties("username");
		}
	}
}
