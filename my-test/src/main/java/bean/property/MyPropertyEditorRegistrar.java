package bean.property;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(String.class, new MySimplePropertyEditor());
		registry.registerCustomEditor(Car.class, new MyBasicPropertyEditor());
	}
}
