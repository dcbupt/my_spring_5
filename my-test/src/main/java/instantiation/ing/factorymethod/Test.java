package instantiation.ing.factorymethod;

public class Test {

	private People people1;
	private People peopleFactory;

	public People getPeopleFactory() {
		return peopleFactory;
	}

	public void setPeopleFactory(People peopleFactory) {
		this.peopleFactory = peopleFactory;
	}

	public People getPeople1() {
		return people1;
	}

	public void setPeople1(People people1) {
		this.people1 = people1;
	}
}
