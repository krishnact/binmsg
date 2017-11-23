package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BitField_8 extends BinPrimitive {
	int value;
	public static String ZERO_32 = "00000000000000000000000000000000";

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int read(DataInputStream istream) throws IOException {
		value = istream.readByte();

		return 1;
	}

	public void dump(PrintStream pstream) {
		pstream.println(toString());
	}

	public void dump(DumpContext dc) {
		dump(dc.getPs());
	}

	public String toString() {
		String strBin = Integer.toBinaryString(value);
		return ("00000000" + strBin).substring(strBin.length());
	}

	public static String toDisplayString(int value, int size) {
		if (size < 9) {
			return toBinString(value, size);
		} else {
			return toHexString(value, size);
		}		
	}

	public static String toBinString(int value, int size) {
	
			String binString = Integer.toBinaryString(value);
			int startIndex = 32 - size + binString.length();
			return ZERO_32.substring(startIndex) + binString;
	
	}

	public static String toHexString(int value, int size) {
		String hexString = Integer.toHexString(value);
		int startIndex = 32 - (size + 3) / 4 + hexString.length();
		return "0x" + ZERO_32.substring(startIndex) + hexString;
	}
}
