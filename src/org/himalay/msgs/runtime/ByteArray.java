package org.himalay.msgs.runtime;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ByteArray implements ComplexBinMessage {
	public static enum enLenghType {
		EOS, FIRST_UI8, FIRST_UI16, FIRST_UI24, FIRST_UI32, FIXED, EXTERNAL, NULLTERMINATED
	};

	byte[] data;
	enLenghType sizeType;

	public ByteArray() {
		super();
	}

	// public ByteArray(byte[] data, String sizeType) {
	// super();
	// this.data = data;
	// setSize(sizeType);
	// }

	// public ByteArray(String sizeType) {
	// this(null, sizeType);
	// }

	// public ByteArray(int size, String sizeType) {
	// this(null, sizeType);
	// data = new byte[size];
	// }

	public int read(DataInputStream istream) throws IOException {
		int iRead = 0;
		switch (sizeType) {
		case EOS: {
			byte[] ba = new byte[1014 * 10];
			if (istream.available() > 0) {
				iRead = istream.read(ba);
				if ( iRead > 0)
				{
				data = new byte[iRead];
				System.arraycopy(ba, 0, data, 0, iRead);
				}else
				{
					iRead = 0;
				}
			} else {
				data = new byte[0];
			}
			break;
		}
		case FIRST_UI8: {
			iRead = 0x00 | istream.read();
			data = new byte[iRead];
			if (data.length > 0)
			iRead = istream.read(data)+1;
			break;
		}
		case FIRST_UI16: {
			iRead = 0x00 | istream.readUnsignedShort();
			data = new byte[iRead];
			if (data.length > 0)
			iRead = istream.read(data)+2;
			break;
		}
		case FIRST_UI24: {
			iRead = BinPrimitive.readUI24(istream);
			data = new byte[iRead];
			if (data.length > 0)
			iRead = istream.read(data)+3;
			break;
		}
		case FIRST_UI32: {
			iRead = (int)BinPrimitive.readUI32(istream);
			data = new byte[iRead];
			if (data.length > 0)
			iRead = istream.read(data)+4;
			break;
		}
		case EXTERNAL: {
			if (data.length > 0)
			iRead = istream.read(data);
			break;
		}
		case FIXED: {
			if (data.length > 0)
			iRead = istream.read(data);
			break;
		}
		case NULLTERMINATED:
			this.data	= readZString(istream);
			if (data.length > 0)
			iRead	= this.data.length;
			break;
		}

		return iRead;
	}

	private byte[] readZString(InputStream is) throws IOException
	{
		// Allocate a byte byffer
		int buffLength	= 1024;
		byte[] retVal	= new byte[buffLength];
		int iCnt		= 0;
		int justRead	=0;
		
		do 
		{
			justRead = is.read();
			retVal[iCnt++]= (byte)justRead;
			if ( iCnt == retVal.length)
			{
				buffLength+= 1024;
				byte [] newBuff	= new byte[buffLength];
				System.arraycopy(retVal, 0, newBuff, 0, retVal.length);
				retVal	= newBuff;
			}
		}while( justRead != 0);
		byte[] rr = new byte[iCnt];
		System.arraycopy(retVal, 0, rr, 0, rr.length);
		return rr;
	}
	
	public void setSizeType(String size) {
		this.sizeType = enLenghType.valueOf(size);
	}

	public void setSize(String size) {
		setSize(Integer.parseInt(size));
	}

	public void setSize(int size) {
		data = new byte[size];
	}

	public void dump(DumpContext dc) {
		// pstream.print("Dumping " + this.data.length+"\n");
		// HexDump.dumpHexData(pstream, "", this.data, this.data.length);

		dump(dc, this.data);
	}

	
	public static void dump(DumpContext dc, byte [] bytes) {
		// pstream.print("Dumping " + this.data.length+"\n");
		// HexDump.dumpHexData(pstream, "", this.data, this.data.length);

		int width = 16;
		if ( bytes != null)
		{
		try {
				for (int index = 0; index < bytes.length; index += width) {
					dc.getPs().print(dc.getIndentString());
					printHex(dc, bytes, index, width);
					printAscii(dc, bytes, index, width);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			dc.indent(); dc.ps.println("null");
		}
	}
	
	public static void dump(DumpContext dc, byte [] bytes, int offsetStart, int length) {
		// pstream.print("Dumping " + this.data.length+"\n");
		// HexDump.dumpHexData(pstream, "", this.data, this.data.length);

		int width = 16;
		
		if ( bytes != null)
		{
			int iEnd = (offsetStart + length )< bytes.length?(offsetStart + length ):bytes.length;
			try {
				for (int index = offsetStart; index < iEnd ; index += width) {
					dc.getPs().print(dc.getIndentString());
					printHex(dc, bytes, index, width);
					printAscii(dc, bytes, index, width);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			dc.indent(); dc.ps.println("null");
		}
	}
	@Override
	public int getSize() {
		if (this.data != null)
			return this.data.length;
		else
			return 0;
	}

	public int write(DataOutputStream ostream) throws IOException {
		// if the length is not external then we have to write length also
		int retVal	 = 0;
		switch (sizeType) {
		case FIRST_UI16:
			if ( this.data != null)
			{
				ostream.write(this.data.length >> 8);
				ostream.write(this.data.length);
			}else
			{
				ostream.writeShort(0);
			}
			retVal +=2;
			ostream.write(data);
			break;
		case FIRST_UI8:
			if ( this.data != null)
			{
				ostream.write(this.data.length);
			}else
			{
				ostream.write(0);
			}
			ostream.write(data);
			retVal +=1;
			break;
		case FIRST_UI24:
		{
			int iWrt	= 0;
			if ( this.data != null)
			{
				iWrt = this.data.length;
			}
			ostream.write((iWrt >> 16)&(0x000000FF));
			ostream.write((iWrt >> 8)&(0x000000FF));
			ostream.write((iWrt)&(0x000000FF));

			retVal +=3;
			ostream.write(data);
			break;
		}
		case FIRST_UI32:
			int iWrt	= 0;
			if ( this.data != null)
			{
				iWrt = this.data.length;
			}
			ostream.writeInt(iWrt);
			retVal +=4;
			ostream.write(data);
			break;
		case EOS:
		case EXTERNAL:
		case FIXED:
			if ( this.data != null)
				ostream.write(data);
			break;
		case NULLTERMINATED:
			if ( this.data != null)
			{
				ostream.write(data);
			}
			ostream.write(0);
			break;
		}
		retVal += data.length;
		return retVal;
	}

	public void setData(byte[] newData) {
		if ( this.sizeType == enLenghType.FIXED)
		{
			this.copyData(newData);
		}else
			this.data = newData;
	}

	public void copyData(byte[] newData, int offset, int length)
	{
		System.arraycopy(newData, offset, this.data, 0, length>this.data.length?this.data.length:length);
	}
	
	public void copyData(byte[] newData)
	{
		copyData(newData,0,newData.length);
	}
	
	private static void printHex(DumpContext dc, byte[] bytes, int offset,
			int width) {
		for (int index = 0; index < width; index++) {
			if (index + offset < bytes.length) {
				dc.getPs().printf("%02x ", bytes[index + offset]);
			} else {
				dc.getPs().print("   ");
			}
		}
	}

	private static void printAscii(DumpContext dc, byte[] bytes, int index,
			int width) throws UnsupportedEncodingException {
		if (index < bytes.length) {
			width = Math.min(width, bytes.length - index);
			//String str	= new String(bytes, index, width, "UTF-8");
			byte[] bytes1	= new byte[width];//str.getBytes();
			System.arraycopy(bytes, index, bytes1,0,width);
			for (int i = 0; i < bytes1.length; i++) {
				if ( bytes1[i] < 32)
				{
					bytes1[i] = '.';
				}
			}
			dc.getPs().println(new String(bytes1));
		} else {
			dc.getPs().println();
		}
	}

	public byte [] getData()
	{
		return data;
	}
	
	public static DataInputStream createDataInputStream(byte[] data, int offset, int length){
		return new DataInputStream(new ByteArrayInputStream(data, offset, length));
	}
	
	public static DataInputStream createDataInputStream(byte[] data){
		return createDataInputStream(data, 0, data.length);
	}
	
	public String getAsStringFromUTF8() throws UnsupportedEncodingException
	{
		return new String(this.getData(),"UTF-8");
	}
	
	public void setFromUTF8String(String strData) throws UnsupportedEncodingException
	{
		 setData(strData.getBytes("UTF-8"));
	}

	public String getAsASCIIFromUTF8() throws UnsupportedEncodingException
	{
		return new String(this.getData(),"ASCII");
	}
	
	public void setFromASCIIString(String strData) throws UnsupportedEncodingException
	{
		 setData(strData.getBytes("ASCII"));
	}
	
	@Override
	public String toString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.dump(new DumpContext(baos));
		return new String(baos.toByteArray());
	}
}
