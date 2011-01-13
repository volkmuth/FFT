package com.example.alg;

import org.junit.Test;

import junit.framework.TestCase;

public class HzToKeyTest extends TestCase {

	public static final double cent = Math.log(2)/1200;
	@Test
	public void testToHalfSteps() {
		HzToKey convert = new HzToKey();
		int a440 = convert.toHalfSteps(440);
		assertEquals("A440 is 0 steps from A440", 0, a440);
		int middleC = convert.toHalfSteps(261);
		assertEquals("Middle C is 9 steps from A440", -9, middleC);
		middleC = convert.toHalfSteps(256);
		assertEquals("slightly flat middle C is 9 steps from A440", -9, middleC);
		
		int aSharp = convert.toHalfSteps(466);
		assertEquals("A sharp is half step from A440", 1, aSharp);
		aSharp = convert.toHalfSteps(465);
		assertEquals("Slightly flat A sharp is half step from A440", 1, aSharp);
		aSharp = convert.toHalfSteps(453);
		assertEquals("A + half a semitone + epsilon, rounded, is 1 step from A440", 1, aSharp);
		int aSemi = convert.toHalfSteps(452);
		assertEquals("A + half a semitone - epsilon, rounded, is 0 steps from A440", 0, aSemi);
		
		int aFlatSemi = convert.toHalfSteps(428);
		assertEquals("A - half a semitone + epsilon, rounded, is 0 steps from A440", 0, aFlatSemi);
		
		aFlatSemi = convert.toHalfSteps(427);
		assertEquals("A - half a semitone - epsilon, rounded, is -1 step from A440", -1, aFlatSemi);
	}
	
	@Test
	public void testToKeys() {
		HzToKey convert = new HzToKey();
		int a440 = convert.toKeys(440);
		assertEquals("A4 is 49th key on keyboard", 49, a440);
		
		int C4 = convert.toKeys(261);
		assertEquals("C4 is 40th key on keyboard", 40, C4);
		
		int gSharp6 = convert.toKeys(1661);
		assertEquals("G#6 is 72nd key on keyboard", 72, gSharp6);
		
		int bFlat3 = convert.toKeys(233);
		assertEquals("Bb3 is 38th key on keyboard", 38, bFlat3);
		
		int bFlat3Minus49 = convert.toKeys(Math.exp(-49 * cent) * 233);
		assertEquals("Bb3 49 cents flat is still 38th key on keyboard", 38, bFlat3Minus49);

		int bFlat3Minus51 = convert.toKeys(Math.exp(-51 * cent) * 233);
		assertEquals("Bb3 51 cents flat is 37th key on keyboard (i.e. A3)", 37, bFlat3Minus51);
	}
	
	@Test
	public void testToKeyName() {
		HzToKey convert = new HzToKey();
		String A440 = convert.toKeyName(440);
		assertEquals("A440 is A4", "A4", A440);
		
		String C3 = convert.toKeyName(130);
		assertEquals("C below middle C is C3", "C3", C3);
		
		String C5 = convert.toKeyName(532);
		assertEquals("C above A440 is C5", "C5", C5);
		
		String B4 = convert.toKeyName(493);
		assertEquals("B above A440 is B4", "B4", B4);
		
		String B2 = convert.toKeyName(123);
		assertEquals("B below C3 is B2", "B2", B2);
	}
}
