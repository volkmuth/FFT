package com.example.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChartDataTest {
	private double[] x, y;
	private ChartData data;
	private static final double tol = 0.001;
	
	@Before
	public void setUp() throws Exception {
		x = new double[100];
		y = new double[100];
		for (int i = 0; i < 100; i++) {
			x[i] = i;
			y[i] = (i - 50) * (i - 50) + 5;
		}
		data = new ChartData(x, y);
	}

	@Test
	public void testGetRangeMin() {
		assertEquals("Minimum for range", 5, data.getRangeMin(), tol);
	}

	@Test
	public void testGetRangeMax() {
		assertEquals("Maximum for range", 2505, data.getRangeMax(), tol);
	}

	@Test
	public void testGetDomainMin() {
		assertEquals("Minimum for domain", 0, data.getDomainMin(), tol);
	}

	@Test
	public void testGetDomainMax() {
		assertEquals("Max for domain", 99, data.getDomainMax(), tol);
	}

	@Test
	public void testSetXYValues() {
		double[] newX = new double[50];
		double[] newY = new double[50];
		for (int i = 0; i < 50; i++) {
			newX[i] = 49 - i;
			newY[i] = (i - 25) * (25 - i) - 15;
		}
		data.setXYValues(newX, newY);
		assertEquals("Min for domain", 0, data.getDomainMin(), tol);
		assertEquals("Max for domain", 49, data.getDomainMax(), tol);
		assertEquals("Min for range", -640, data.getRangeMin(), tol);
		assertEquals("Max for range", -15, data.getRangeMax(), tol);
		assertEquals("Number of x values", 50, data.getXValues().length);
		assertEquals("Number of y values", 50, data.getYValues().length);
	}

}
