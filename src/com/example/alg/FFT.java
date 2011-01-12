package com.example.alg;

public class FFT {
	public FFT() {
	}

	protected void bitReverse(final Complex[] in) {
		int N = in.length;
		int target = 0;
		for (int pos = 1; pos < N; pos++) {
			int currentBit = N >> 1; // N is a power of 2, so currentBit is msb (only use log2 N bits so we >> 1)
			// implement a counter with reversed bits (simple add & carry)
			while ((currentBit & target) != 0) {
				target &= ~currentBit; // add 1 (i.e. reset bit)
				currentBit >>= 1; // shift bit down one and continue add 1
			}
			target |= currentBit; // carry (or lsb of counter, or overflow i.e. currentBit = 0 which would be an error)
			// make sure we reorder a given element only once: just do it when target > pos.
			// Otherwise we'll go back and forth for the elements and they'll end up in
			// the same order they were in originally.
			if (target > pos) {
				Complex t = in[pos];
				in[pos] = in[target];
				in[target] = t;
			}
		}
	}
	
	public void forwardInPlace(Complex[] x) {
		bitReverse(x);
		compute(x, false);
	}
	
	public void inverseInPlace(Complex[] x) {
		bitReverse(x);
		compute(x, true);
		// normalize
		final Complex scale = new Complex(1.0/x.length);
		for (int i = 0; i < x.length; i++) {
			x[i].mult(scale);
		}
	}
	
	public Complex[] forward(final Complex[] x) {
		Complex[] out = new Complex[x.length];
		for (int i = 0; i < x.length; i++) {
			out[i] = new Complex(x[i]);
		}
		forwardInPlace(out);
		return out;
	}
	
	public Complex[] inverse(final Complex[] x) {
		Complex[] out = new Complex[x.length];
		for (int i = 0; i < x.length; i++) {
			out[i] = new Complex(x[i]);
		}
		inverseInPlace(out);
		return out;
	}
	
	private void compute(final Complex[] x, final boolean invert) {
		final int N = x.length;
		Complex t2 = new Complex();
		Complex factor = new Complex();
		final double theta = Math.PI * (invert ? 1 : -1); 
		for (int s = 1; s < N; s <<= 1) {
			final int j = s << 1;
			final double delta = theta / s;
			factor.set(1, 0);
			for (int g = 0; g < s; g++) {
				for (int p = g; p < N; p += j) {
					final int m = p + s;
					t2.assign(x[m]);
					if (g > 0) {
						t2.mult(factor);
					}
					x[m].assign(x[p]);
					x[m].sub(t2);
					x[p].add(t2);
				}
				factor.phase(delta * (g + 1));
			}
		}
	}
}
