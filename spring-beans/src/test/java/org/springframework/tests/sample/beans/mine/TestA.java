package org.springframework.tests.sample.beans.mine;

import org.springframework.beans.factory.annotation.Autowired;

public class TestA {

	//@Autowired
	private TestB testB;

	public TestB getTestB() {
		return testB;
	}

	public void setTestB(TestB testB) {
		this.testB = testB;
	}

	//public TestA(TestB testB) {
	//	this.testB = testB;
	//}
}
