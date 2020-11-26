package bean.property;

import common.MyTestBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @implNote 自定义PropertyEditor来编辑<property>设置的属性字面量或转换为bean属性的实际类型对象
 * @implNote see AbstractAutowireCapableBeanFactory#applyPropertyValues
 */
public class PropertyEditorTest {

	@Test
	@SuppressWarnings("resource")
	public void propertyEditorTest() {
		// 编辑<property>设置的属性字面量
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("bean.property/BeanPropertyEditorTest.xml");
		MyTestBean myTestBean = (MyTestBean) ctx.getBean("myTestBean");
		assertNotNull(myTestBean);
		System.out.println(myTestBean.getName());

		// 将<property>设置的属性字面量转换为bean属性的实际类型对象
		BossBean bossBean = (BossBean) ctx.getBean("bossBean");
		assertNotNull(bossBean);
		assertNotNull(bossBean.getCar());
		System.out.println(bossBean.getCar().getBrand()+bossBean.getCar().getMaxSpeed()+bossBean.getCar().getPrice());
	}

	/**
	 * @apiNote PropertyEditor不适用于注解@Bean配置的bean
	 *
	 * 原因：
	 * @Bean配置的bean，bd.pv为空。调用@Bean修饰的工厂方法实例化。bean属性在工厂方法里赋值
	 */
	@Test
	@SuppressWarnings("resource")
	public void propertyEditorFailTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfigForPE.class);
		BossBean bossBean = (BossBean) ctx.getBean("bossBean");
		assertNotNull(bossBean);
		assertNull(bossBean.getCar());
	}

}
