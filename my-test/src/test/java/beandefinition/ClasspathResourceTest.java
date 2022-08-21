package beandefinition;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ClasspathResourceTest {

	@Test
	public void testClassPathResource() throws IOException {
		/*指定Class，使用该class的cl去加载xml资源文件，path=文件在Class所在包下的相对路径*/
		Resource resource = new ClassPathResource("xml/BeanDefinitionTest.xml", TestBean.class);
		assertNotNull(resource.getInputStream());
	}

	@Test(expected = FileNotFoundException.class)
	public void testClassPathResource2() throws IOException {
		/*不指定Class，使用appCL加载资源文件，path=文件在cl下的相对路径*/
		Resource resource = new ClassPathResource("xml/BeanDefinitionTest.xml");
		assertNull(resource.getInputStream());
	}

	@Test
	public void testClassPathResource3() throws IOException {
		/*不指定Class，使用appCL加载资源文件，path=文件在cl下的相对路径*/
		Resource resource = new ClassPathResource("beandefinition/xml/BeanDefinitionTest.xml");
		assertNotNull(resource.getInputStream());
	}

	@Test
	public void testClassPathResource4() throws IOException {
		/*不指定Class，使用appCL加载资源文件，path=文件在cl下的相对路径*/
		Resource resource = new ClassPathResource("beandefinition/xml/My.xml");
		assertNotNull(resource.getInputStream());
	}

}
