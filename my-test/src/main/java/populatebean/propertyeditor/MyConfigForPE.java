package populatebean.propertyeditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigForPE {

	@Bean
	public BossBean bossBean() {
		BossBean bossBean = new BossBean();
		bossBean.setName("dc");
		return bossBean;
	}

	@Bean
	public CustomEditorConfigurer customEditorConfigurer() {
		CustomEditorConfigurer cec = new CustomEditorConfigurer();
		cec.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{new MyPropertyEditorRegistrar()});
		return cec;
	}
}
