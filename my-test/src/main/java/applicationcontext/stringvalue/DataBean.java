package applicationcontext.stringvalue;

import org.springframework.stereotype.Component;

@Component
public class DataBean {

	private String name = "dc";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
