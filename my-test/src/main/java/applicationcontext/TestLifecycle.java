package applicationcontext;

import org.springframework.context.Lifecycle;

public class TestLifecycle implements Lifecycle {

	private boolean isRunning = false;

	@Override
	public void start() {
		System.out.println("TestLifecycle start");
		isRunning = true;
	}

	@Override
	public void stop() {
		System.out.println("TestLifecycle stop");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}
}
