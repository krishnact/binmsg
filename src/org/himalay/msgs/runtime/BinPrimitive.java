package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BinPrimitive {

	public static int readI24(DataInputStream istream) throws IOException
	{
		int i1	= (0x000000FF & ((int)istream.read())) << 16;
		int i2	= (0x0000FFFF & ((int)istream.readUnsignedShort()));  
		return i1 | i2;     
	}
	
	public static int readUI24(DataInputStream istream) throws IOException
	{
		int i1	= (0x000000FF & ((int)istream.read())) << 16;
		int i2	= (0x0000FFFF & ((int)istream.readUnsignedShort()));  
		return i1 | i2;     
	}
	
	public static long readUI32(DataInputStream istream) throws IOException
	{
		long i1	= istream.readUnsignedShort();
		long i2	= istream.readUnsignedShort();
		return ( i1<<16) | i2;     
	}
	
	public static void writeUI32(DataOutputStream ostream, long value) throws IOException
	{
		ostream.writeShort((short)((value & 0x00000000FFFF0000L)>> 16));
		ostream.writeShort((short)(value & 0x000000000000FFFFL));
	}
	
	public static void writeUI24(DataOutputStream ostream, int value) throws IOException
	{
		int retVal = 0;
		// write value
		{
			ostream.writeByte((value & 0x00FF0000) >> 16);
			ostream.writeShort((value & 0x0000FFFF));
			retVal += 3;
		}
	}
	/**
	 * Gets a signed integer from an I24 stored as int
	 */
	public static int signed24Bit(int i24) {
		if ((i24 & 0x800000) != 0) {
			return (0xfffffe - i24);
		}else
		{
			return i24;
		}
	}
	
}
