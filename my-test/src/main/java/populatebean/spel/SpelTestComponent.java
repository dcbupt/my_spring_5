package populatebean.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpelTestComponent {

	@Value("#{myTestBean.name}")
	private String spelUseName;

	public String getSpelUseName() {
		return spelUseName;
	}

	public void setSpelUseName(String spelUseName) {
		this.spelUseName = spelUseName;
	}

}
