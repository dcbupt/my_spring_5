package instantiation.before.factorybean;

import common.MyTestBean;

public class Test {

	private MyTestBean myTestBean;

	public MyTestBean getMyTestBean() {
		return myTestBean;
	}

	public void setMyTestBean(MyTestBean myTestBean) {
		this.myTestBean = myTestBean;
	}
}
