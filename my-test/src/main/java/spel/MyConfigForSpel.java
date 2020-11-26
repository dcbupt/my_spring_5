package spel;

import common.MyTestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"spel"})
public class MyConfigForSpel {

	@Bean
	public MyTestBean myTestBean() {
		MyTestBean myTestBean = new MyTestBean();
		myTestBean.setName("dc");
		return myTestBean;
	}

}
