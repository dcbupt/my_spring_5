package populatebean.autowiremode;

public class PolicemanByType {

	private Gun gun;

	// MyWeapon类型的bean有两个，分别是MyWeapon和他的子类MyWeapon2，没有指定@Primary时，Spring无法确定该依赖注入哪个bean
	private MyWeapon myWeapon;

	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
	}

	public MyWeapon getMyWeapon() {
		return myWeapon;
	}

	public void setMyWeapon(MyWeapon myWeapon) {
		this.myWeapon = myWeapon;
	}
}
