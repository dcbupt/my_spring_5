package instantiation.ing.factorymethod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationPeopleFactory {

	@Bean
	public People people() {
		People people = new People();
		people.setAge(27);
		people.setName("dc");
		return people;
	}
}
