package com.example.alg;

public class Spectrum {
	private Complex c1;
	private Complex c2;
	public Spectrum() { c1 = new Complex();
		c2 = new Complex();
	}

	void calc(Complex[] X, double[] f, int start, int N) {
		final int buffSize = f.length;
		// get previous f0 (will subtract from X_k)
		final double f0 = f[(start + buffSize - 1) % buffSize];
		// get new fN
		final double fN = f[(start + N) % (buffSize)];
		for (int k = 0; k < N; k++) {
			c1.set(f0, 0);
			X[k].sub(c1);
			c1.set(fN, 0);
			X[k].add(c1);
			c2.phase(2 * Math.PI * k/N);
			X[k].mult(c2);
		}
	}
}
