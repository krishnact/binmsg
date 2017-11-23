package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BitField_32 extends BinPrimitive {
	int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int read(DataInputStream istream) throws IOException {
		value = istream.readInt();

		return 4;
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
		return ("00000000000000000000000000000000"+strBin).substring(strBin.length());
	}
}
