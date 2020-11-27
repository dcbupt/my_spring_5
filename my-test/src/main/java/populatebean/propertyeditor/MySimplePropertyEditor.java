package populatebean.propertyeditor;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class MySimplePropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			setValue("edit_"+text);
		}
		else {
			setValue(null);
		}
	}
}
