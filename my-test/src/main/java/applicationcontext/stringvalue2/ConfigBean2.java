package applicationcontext.stringvalue2;

import applicationcontext.configclassparse.ImportedBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan
@Import(ImportedBean.class)
public class ConfigBean2 {

	@Bean
	public PropertySourcesPlaceholderConfigurer ppc() {
		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
		// 调用this.getClass，classpath的根路径就是该类所在目录！所以完整的路径就是:applicationcontext/stringvalue2/applicationcontext/test.properties
//		Resource resource = new ClassPathResource("applicationcontext/test.properties", this.getClass());
		// 不加this.getClass，classpath的根路径就是/
		Resource resource = new ClassPathResource("applicationcontext/test.properties");
		ppc.setLocation(resource);
		return ppc;
	}
}
