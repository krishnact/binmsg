package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BitField_48 extends BinPrimitive {
	long value;

	public long getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int read(DataInputStream istream) throws IOException {
		long i1	= istream.readUnsignedShort() ;
		long i2	= istream.readUnsignedShort() ;
		long i3	= istream.readUnsignedShort() ;
		value = i1 << 32 | i2 << 16 | i3;

		return 6;
	}

	public void dump(PrintStream pstream) {
		pstream.println(toString());
	}
	
	public void dump(DumpContext dc) {
		dump(dc.getPs());
	}
	
	
	public String toString()
	{
		String strBin	= Long.toBinaryString(value); 
		return ("000000000000000000000000000000000000000000000000"+strBin).substring(strBin.length());
	}
}
