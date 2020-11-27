package instantiation.before.lookup;

public class CoffeeLover {

	// byName注入，不存在beanName=coffee时，coffee=null
	private Coffee coffee;

	public Coffee getCoffee() {
		return coffee;
	}

	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}

	/**
	 * lookup方法，从配置获取实际的返回类型
	 * bean的lookup方法会被CgLib代理，拦截器从bd里获取lookup方法的实际beanName，加载后作为返回值
	 * @return
	 */
	public Coffee lookupCoffee() {
		return null;
	}

	public String getCoffeeBrand() {
		return lookupCoffee().getCoffeeBrand();
	}
}
