package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BitField_24 extends BinPrimitive {
	int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int read(DataInputStream istream) throws IOException {
		value = (istream.readUnsignedByte() <<16) | istream.readUnsignedShort();

		return 3;
	}

	public void dump(PrintStream pstream) {
		pstream.println(toString());
	}

	public void dump(DumpContext dc) {
		dump(dc.getPs());
	}
	public String toString()
	{
		String strBin	= Integer.toBinaryString(value); 
		return ("000000000000000000000000"+strBin).substring(strBin.length());
	}
}
