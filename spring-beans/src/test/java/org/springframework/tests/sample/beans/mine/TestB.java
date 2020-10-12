package org.springframework.tests.sample.beans.mine;

import org.springframework.beans.factory.annotation.Autowired;

public class TestB {

	//@Autowired
	private TestA testA;

	public TestA getTestA() {
		return testA;
	}

	public void setTestA(TestA testA) {
		this.testA = testA;
	}

	//public TestB(TestA testA) {
	//	this.testA = testA;
	//}
}
