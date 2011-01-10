package com.example.alg;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComplexTest {
	final static double tol = 0.001;

	@Test
	public void testMult() {
		String op = "PRODUCT";
		Complex z1 = new Complex(1);
		Complex z2 = new Complex(2);
		Complex p = Complex.mult(z1, z2);
		assertEquals("Real part of " + op + " of two reals is " + op + " of the reals", 2, p.re(), tol);
		assertEquals(op + " of two reals is real", 0, p.im(), tol);
		
		z1 = new Complex(0, 1);
		z2 = new Complex(0, 2);
		p = Complex.mult(z1, z2);
		assertEquals("Real part of " + op + " of two imaginaries is " + op + " of imaginaries", -2, p.re(), tol);
		assertEquals(op + " of two imaginaries is real", 0, p.im(), tol);
		
		z1 = new Complex(1, 2);
		z2 = new Complex(3, 4);
		p = Complex.mult(z1, z2);
		assertEquals("Real part of " + op, -5, p.re(), tol);
		assertEquals("Imaginary part of " + op, 10, p.im(), tol);
	}

	@Test
	public void testAddComplex() {
		String op = "SUM";
		Complex z1 = new Complex(-4);
		Complex z2 = new Complex(3);
		Complex p = Complex.add(z1, z2);
		assertEquals("Real part of " + op + " of two reals is " + op + " of the reals", -1, p.re(), tol);
		assertEquals(op + " of two reals is real", 0, p.im(), tol);
		
		z1 = new Complex(0, 1);
		z2 = new Complex(0, 2);
		p = Complex.add(z1, z2);
		assertEquals("Real part of " + op + " of two imaginaries is " + op + " of imaginaries", 3, p.im(), tol);
		assertEquals(op + " of two imaginaries is imaginary", 0, p.re(), tol);
		
		z1 = new Complex(1, 2);
		z2 = new Complex(3, 4);
		p = Complex.add(z1, z2);
		assertEquals("Real part of " + op, 4, p.re(), tol);
		assertEquals("Imaginary part of "+ op, 6, p.im(), tol);
	}

	@Test
	public void testModulus() {
		Complex z = new Complex(1);
		assertEquals("Modulus of a real number is value of number", 1, z.modulus(), tol);
		z = new Complex(2, 1);
		assertEquals("Modulus of complex number is sqrt of SOS", Math.sqrt(5), z.modulus(), tol);
	}

	@Test
	public void testSub() {
		String op = "DIFFERENCE";
		Complex z1 = new Complex(2.4);
		Complex z2 = new Complex(-1.8);
		Complex p = Complex.sub(z2, z1);
		assertEquals("Real part of " + op + " of two reals is " + op + " of the reals", -4.2, p.re(), tol);
		assertEquals(op + " of two reals is real", 0, p.im(), tol);
		
		z1 = new Complex(0, -1.1);
		z2 = new Complex(0, 2.2);
		p = Complex.sub(z2, z1);
		assertEquals("Real part of " + op + " of two imaginaries is " + op + " of imaginaries", 3.3, p.im(), tol);
		assertEquals(op + " of two imaginaries is imaginary", 0, p.re(), tol);
		
		z1 = new Complex(1, 2);
		z2 = new Complex(3, 4);
		p = Complex.sub(z2, z1);
		assertEquals("Real part of " + op, 2, p.re(), tol);
		assertEquals("Imaginary part of "+ op, 2, p.im(), tol);
	}

	@Test
	public void testPhase() {
		Complex z = new Complex();
		z.phase(0);
		assertEquals("Re exp(i*0) is 1", 1, z.re(), tol);
		assertEquals("Im exp(i*0) is 0", 0, z.im(), tol);
		
		z.phase(Math.PI / 2);
		assertEquals("Re exp(i*pi/2) is 0", 0, z.re(), tol);
		assertEquals("Im exp(i*pi/2) is 1", 1, z.im(), tol);

		z.phase(Math.PI * 1.25);
		assertEquals("Re exp(i * 3/4 pi) is -1/sqrt(2)", -1/Math.sqrt(2), z.re(), tol);
		assertEquals("Im exp(i * 3/4 pi) is -1/sqrt(2)", -1/Math.sqrt(2), z.im(), tol);
	}

	@Test
	public void testAssign() {
		Complex z = new Complex(3, 5);
		Complex z2 = new Complex();
		z.assign(z2);
		assertEquals("Re parts agree after assignment op", z2.re(), z.re(), tol);
		assertEquals("Im parts agree after assignment op", z2.im(), z.im(), tol);
	}
	
	@Test
	public void testConjugate() {
		Complex z = new Complex(2, 2);
		Complex r = Complex.conjugate(z);
		assertEquals("Re part of conjugate equals original re part", z.re(), r.re(), tol);
		assertEquals("Im part of conjugate is neg of original im part", -z.im(), r.im(), tol);
	}
}
