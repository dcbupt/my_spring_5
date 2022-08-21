package populatebean.autowiremode;

public class PolicemanByName {

	private Gun ak47;

	private MyWeapon myWeapon;

	private MyWeapon myWeapon2;

	private MyWeapon myWeapon3;

	private String name;

	public MyWeapon getMyWeapon3() {
		return myWeapon3;
	}

	public void setMyWeapon3(MyWeapon myWeapon3) {
		this.myWeapon3 = myWeapon3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gun getAk47() {
		return ak47;
	}

	public void setAk47(Gun ak47) {
		this.ak47 = ak47;
	}

	public MyWeapon getMyWeapon() {
		return myWeapon;
	}

	public void setMyWeapon(MyWeapon myWeapon) {
		this.myWeapon = myWeapon;
	}

	public MyWeapon getMyWeapon2() {
		return myWeapon2;
	}

	public void setMyWeapon2(MyWeapon myWeapon2) {
		this.myWeapon2 = myWeapon2;
	}
}
