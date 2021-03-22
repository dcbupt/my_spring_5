package applicationcontext;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ApplicationListenerTest {

	@Test
	public void testApplicationListener() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"applicationcontext/ApplicationListenerTest.xml");
	}

}
