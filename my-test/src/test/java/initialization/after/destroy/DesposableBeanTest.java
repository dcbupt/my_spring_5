package initialization.after.destroy;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * bean销毁回调方法测试
 * 创建bean销毁回调方法有两种方式：bean实现Disposable接口或者xml里显示指定destroy-method
 *
 * Spring会为支持销毁回调的bean，创建DisposableBeanAdapter并缓存。spring容器调用close方法关闭时，DisposableBeanAdapter会回调bean的Disposable接口方法 && destroy-method方法
 */
public class DesposableBeanTest {


	@Test
	public void desposableBeanTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"initialization/after.destroy/DesposableBeanTest.xml");
		MyDesposableBean myDesposableBean = (MyDesposableBean) ctx.getBean("myDesposableBean");
		assertNotNull(myDesposableBean);
		ctx.close();
	}

}
