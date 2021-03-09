package initialization.before.aware;

import org.springframework.stereotype.Component;

@Component
public class NBean {

	public void sayHi() {
		System.out.println("hi");
	}
}
