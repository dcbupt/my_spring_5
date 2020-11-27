package instantiation.ing.factorymethod;

public class PeopleFactory {

	public static People createPeople() {
		People people = new People();
		people.setAge(27);
		people.setName("dc");
		return people;
	}
}
