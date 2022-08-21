package applicationcontext.stringvalue2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueBeanUsePpc {

	@Value("${abc}")
	private String abc;

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}
}
