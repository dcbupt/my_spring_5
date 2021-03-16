package populatebean.autowire;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;

public class AutowireTest {

	@Test
	@SuppressWarnings("resource")
	public void autowireModeByTypeTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				App.class);
		Bean1 bean1 = (Bean1)ctx.getBean("bean1");
		assertNotNull(bean1);
		assertNotNull(bean1.getBean2());
	}

}
