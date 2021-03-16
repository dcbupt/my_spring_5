package initialization.after.destroy;

import org.springframework.beans.factory.DisposableBean;

public class MyDesposableBean implements DisposableBean {

	public void xmlDestroyMethod() {
		System.out.println("MyDesposableBean xmlDestroyMethod");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("MyDesposableBean destroy");
	}
}
