package com.example.alg;

import static org.junit.Assert.*;

import org.junit.Test;

public class FFTTest {
	final static double tol = 0.001;

	@Test
	public void testBitReverseComplexArrayComplexArray() {
		int[] order = {0, 4, 2, 6, 1, 5, 3, 7};
		Complex[] in = new Complex[8];
		Complex[] out = new Complex[8];
		for (int i = 0; i < 8; i++) {
			in[i] = new Complex(i);
			out[i] = in[i];
		}
		FFT fft = new FFT();
		fft.bitReverse(out);
		for (int i = 0; i < 8; i++) {
			assertEquals("Reordered element " + i, in[order[i]], out[i]);
		}
		
		order = new int[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		in = new Complex[16];
		out = new Complex[16];
		for (int i = 0; i < 16; i++) {
			in[i] = new Complex(i);
			out[i] = in[i];
		}
		fft.bitReverse(out);
		for (int i = 0; i < 16; i++) {
			assertEquals("Reordered element from 16 dim vector " + i, in[order[i]], out[i]);
		}
	}

	@Test
	public void testForward() {
		FFT fft = new FFT();
		// start with a trivial case: N=1, value = 1.  Should be pure constant.
		Complex[] testF = { new Complex(2) };
		Complex[] result = fft.forward(testF);
		assertEquals("FFT of real const is real", 0, result[0].im(), tol);
		assertEquals("FFT of const is itself", 2, result[0].re(), tol);

		testF = new Complex[] { new Complex(2), new Complex(2) };
		result = fft.forward(testF);
		assertEquals("First component of const vector FFT is non-zero", 4, result[0].re(), tol);
		assertEquals("Other components of const vector FFT are zero", 0, result[1].abs2(), tol);
		
		testF = new Complex[16];
		for (int i = 0; i < 16; i++) {
			testF[i] = new Complex(Math.cos(Math.PI * 2 / 16 * i));
		}
		fft = new FFT();
		result = fft.forward(testF);
		assertEquals("First component of cos FFT is 0", 0, result[0].abs2(), tol);
		assertEquals("16 cos FFT : real(1) = 8", 8, result[1].re(), tol);
		assertEquals("16 cos FFT : real(15)= 8", 8, result[1].re(), tol);
		assertEquals("16 cos FFT : im(1) = 0", 0, result[1].im(), tol);
		Complex[] original = fft.inverse(result);
		for (int i = 0; i < testF.length; i++) {
			assertEquals("Round trip: real component of datapoint " + i, testF[i].re(), original[i].re(), tol);
			assertEquals("Round trip: imaginary component of datapoint " + i, testF[i].im(), original[i].im(), tol);
		}
		
		// Round trip for arb function recovers function
		testF = new Complex[16];
		for (int i = 0; i < 16; i++) {
			testF[i] = new Complex(2 * i * i, -3 + i - Math.sqrt(i));
		}
		result = fft.forward(testF);
		original = fft.inverse(result);
		for (int i = 0; i < testF.length; i++) {
			assertEquals("Round trip: real component of datapoint " + i, testF[i].re(), original[i].re(), tol);
			assertEquals("Round trip: imaginary component of datapoint " + i, testF[i].im(), original[i].im(), tol);
		}
	}
}
