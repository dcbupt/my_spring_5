package initialization.ing.afterpropset;

import org.springframework.beans.factory.InitializingBean;

public class AfterPropSetBean implements InitializingBean {

	private String argFromConf;

	private String followedArg;

	public String getArgFromConf() {
		return argFromConf;
	}

	public void setArgFromConf(String argFromConf) {
		this.argFromConf = argFromConf;
	}

	public String getFollowedArg() {
		return followedArg;
	}

	public void setFollowedArg(String followedArg) {
		this.followedArg = followedArg;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		followedArg = "after_prop_set_"+argFromConf;
	}
}
