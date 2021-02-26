package populatebean.autowire;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowireTest {

	@Test
	@SuppressWarnings("resource")
	public void autowireModeByTypeTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				App.class);
	}

}
