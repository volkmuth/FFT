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
		assertEquals("16 cos FFT : real(15)= 8", 8, result[15].re(), tol);
		assertEquals("16 cos FFT : im(1) = 0", 0, result[1].im(), tol);
		assertEquals("16 cos FFT : im(15) = 0", 0, result[15].im(), tol);
		Complex[] original = fft.inverse(result);
		for (int i = 0; i < testF.length; i++) {
			assertEquals("Round trip: real component of datapoint " + i, testF[i].re(), original[i].re(), tol);
			assertEquals("Round trip: imaginary component of datapoint " + i, testF[i].im(), original[i].im(), tol);
		}
		
		testF = new Complex[16];
		for (int i = 0; i < 16; i++) {
			testF[i] = new Complex(Math.sin(Math.PI * 2 / 16 * i));
		}
		fft = new FFT();
		result = fft.forward(testF);
		assertEquals("First component of sin FFT is 0", 0, result[0].abs2(), tol);
		assertEquals("16 sin FFT : real(1) = 0", 0, result[1].re(), tol);
		assertEquals("16 sin FFT : real(15)= 0", 0, result[15].re(), tol);
		assertEquals("16 sin FFT : im(1) = -8", -8, result[1].im(), tol);
		assertEquals("16 sin FFT : im(15) = 8", 8, result[15].im(), tol);
		original = fft.inverse(result);
		for (int i = 0; i < testF.length; i++) {
			assertEquals("Round trip sin: real component of datapoint " + i, testF[i].re(), original[i].re(), tol);
			assertEquals("Round trip sin: imaginary component of datapoint " + i, testF[i].im(), original[i].im(), tol);
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

	@Test
	public void testTimeSequence() {
		// Test calculation of spectrum at t + 1 from spectrum at t using 
		// X_k[t+1]=exp(2pi i k/N) * (X_k[t] - f0[t] + fN[t])
		Spectrum s = new Spectrum();
		// Var to hold the spectrum calculations
		Complex[] spec = new Complex[16];
		for (int i = 0; i < 16; i++) {
			spec[i] = new Complex();
		}
		// Some made up data--time series with f(t)=2 * t^2 - 3 * sqrt(t)
		// Calculate the spectrum at each time point
		double[] buff = new double[64];
		for (int start = 32; start < 48; start++) {
			final double d = 2 * (start + 16) * (start + 16)- 3 * Math.sqrt((start + 16));
			buff[start + 16] = d;
			s.calc(spec, buff, start, 16);
		}

		// Calculate a standard FFT off of the last 16 time points
		Complex[] testF = new Complex[16];
		
		for (int i = 48; i < 64; i++) {
			final double d = 2 * i * i - 3 * Math.sqrt(i);
			testF[i-48] = new Complex(d);
		}
		FFT fft = new FFT();
		//Complex[] result = fft.forward(testF);
		// Invert the spectrum calculated time-point by time-point
		Complex[] original = fft.inverse(spec);
		// Compare the inverted spectrum to the original data
		for (int i = 0; i < testF.length; i++) {
			assertEquals("Inverse from inc spec: real component of datapoint " + i, testF[i].re(), original[i].re(), tol);
			assertEquals("Inverse from inc spec: imaginary component of datapoint " + i, testF[i].im(), original[i].im(), tol);
		}
	}
}
