package populatebean.autowiremode;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import static org.junit.Assert.assertNotNull;

/**
 * @implNote xml配置bean的autowire属性，byType按类型加载依赖，byName按bean的属性名加载依赖
 * byName需要保证bean的属性名是beanName，否则无法注入依赖bean。byType按类型，不限制属性名
 * @implNote see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean
 */
public class AutowireModeTest {

	@Test
	@SuppressWarnings("resource")
	public void autowireModeByTypeTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"populatebean/autowiremode/AutowireModeTestByType.xml");
		PolicemanByType policemanByType = (PolicemanByType) ctx.getBean("policemanByType");
		assertNotNull(policemanByType);
		assertNotNull(policemanByType.getGun());
		policemanByType.getGun().shoot();
	}

	/**
	 * @apiNote byName需要保证bean的属性名是beanName，否则无法注入依赖bean
	 */
	@Test
	@SuppressWarnings("resource")
	public void autowireModeByNameTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"populatebean/autowiremode/AutowireModeTestByName.xml");
		PolicemanByName policemanByName = (PolicemanByName) ctx.getBean("policemanByName");
		assertNotNull(policemanByName);
		assertNotNull(policemanByName.getAk47());
		assertNotNull(policemanByName.getMyWeapon());
		assertNotNull(policemanByName.getMyWeapon2());
	}

}
