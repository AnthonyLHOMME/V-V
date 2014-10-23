package fr.istic.m2gl.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MyClassTestKilled {

	private MyClass source;
	
	@Before
	public void init() {
		source = new MyClass();	
	}
	
	@Test
	public void test_inf() {
		assertEquals(source.myMethod(2, 8), 1);
	}

	@Test
	public void test_sup() {
		assertEquals(source.myMethod(8, 2), -1);
	}

	@Test
	public void test_eq() {
		assertEquals(source.myMethod(4, 4), -1);
	}
}
