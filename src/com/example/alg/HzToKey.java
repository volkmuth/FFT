package com.example.alg;

public class HzToKey {
	public HzToKey() {}
	// A4 a.k.a. concert A a.k.a. A440 is the 49th key on the keyboard
	// (counting from the left)
	public int toHalfSteps(double hz) {
		double halfSteps = 12 * Math.log(hz/440)/Math.log(2);
		
		return (int) Math.round(halfSteps);
	}
	
	public int toKeys(double hz) {
		return 49 + toHalfSteps(hz);
	}

	// MIDI note #60 = C4, so A4 is 69.
	public int toMidi(double hz) {
		return 69 + toHalfSteps(hz);
	}

	public static final String[] octaveKeyName =  { 
		"A", "A#", "B", "C", "C#", "D", "D#",
		"E", "F", "F#", "G", "G#"
	};
	public static final int octave = 12; // 12 half-steps in an octave
	
	public String toKeyName(double hz) {
		// Calculate number of steps from A440, use that to get note
		int steps = toHalfSteps(hz);
		// we want # of steps modulo an octave, but steps could be
		// negative.  Java's % is a remainder operator, so e.g.
		// -11 % 12 = -11.  To get things to behave correctly we
		// get the remainder, then add an octave, then get rem again
		int offset = ((steps % octave) + octave) % octave;

		// Now figure out which octave it is.  A440 is in the 4th octave
		// with middle C being C4, and the C above A440 being C5.  Since
		// we're measuring # of steps relative to A440 our octave
		// calculations needs to be shifted by 9 half steps upwards
		int whichOctave;
		if (steps < 0) {
			whichOctave = 4 + ((steps + 9) - octave + 1) / octave;
		} else {
			whichOctave = 4 + ((steps + 9)/ octave);
		}
		return octaveKeyName[offset] + whichOctave;
	}
}
