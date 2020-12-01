package initialization.ing.initmethod;

public class InitMethodBean {

	private String initArg;

	public String getInitArg() {
		return initArg;
	}

	public void setInitArg(String initArg) {
		this.initArg = initArg;
	}

	public void init() {
		this.initArg = "inited";
	}
}
