package com.example.alg;

public class Complex {
	private double x;
	private double y;

	public Complex() {
		x = 0;
		y = 0;
	}

	public Complex(Complex z) {
		x = z.re();
		y = z.im();
	}
	
	public Complex(double x) {
		this.x = x;
	}
	
	public Complex(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double im() {
		return y;
	}
	public double re() {
		return x;
	}

	public Complex mult(Complex z) {
		mult(this, z, this);/*
		double r, i;
		r = x * z.x - y * z.y;
		i = x * z.y + y * z.x;
		x = r;
		y = i;
		*/
		return this;
	}

	public Complex add(Complex z) {
		add(z, this, this);
		//x += z.x;
		//y += z.y;
		return this;
	}

	public Complex sub(Complex z) {
		sub(this, z, this);
		//x -= z.x;
		//y -= z.y;
		return this;
	}
	
	public Complex conjugate() {
		conjugate(this, this);
		return this;
	}
	
	public double modulus() {
		return Math.sqrt(abs2());
	}

	public double abs2() {
		return x * x + y * y;
	}

	public static Complex add(final Complex z1, final Complex z2) {
		Complex result = new Complex(z1);
		result.add(z2);
		return result;
	}

	public static Complex sub(Complex z1, Complex z2) {
		Complex result = new Complex(z1);
		result.sub(z2);
		return result;
	}
	
	public static Complex mult(final Complex z1, final Complex z2) {
		Complex result = new Complex(z1);
		result.mult(z2);
		return result;
	}

	public Complex phase(final double theta) {
		x = Math.cos(theta);
		y = Math.sin(theta);
		return this;
	}

	public Complex assign(final Complex complex) {
		x = complex.re();
		y = complex.im();
		return this;
	}
	
	public static Complex conjugate(final Complex z) {
		Complex result = new Complex(z);
		Complex.conjugate(result, result);
		return result;
	}
	
	private static void mult(final Complex z1, final Complex z2, Complex result) {
		double r, i;
		r = z1.x * z2.x - z1.y * z2.y;
		i = z1.y * z2.x + z1.x * z2.y;
		result.x = r;
		result.y = i;
	}
	
	private static void add(final Complex z1, final Complex z2, Complex result) {
		double r, i;
		r = z1.x + z2.x;
		i = z1.y + z2.y;
		result.x = r;
		result.y = i;
	}
	
	private static void sub(final Complex z1, final Complex z2, Complex result) {
		double r, i;
		r = z1.x - z2.x;
		i = z1.y - z2.y;
		result.x = r;
		result.y = i;
	}
	
	private static void conjugate(final Complex z, Complex result) {
		double r, i;
		r = z.x;
		i = -z.y;
		result.x = r;
		result.y = i;
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
