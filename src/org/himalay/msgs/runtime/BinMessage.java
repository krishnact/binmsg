package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public abstract class BinMessage extends BinStruct implements IBinMsg{
	abstract public int readNoHeader(DataInputStream dis) throws IOException;

	public byte[] toByteArray()
	{
		return toByteArray(this);
	}
	
	public static byte[] toByteArray(IBinMsg binMsg)
	{

			try {
				ByteArrayOutputStream baos	= new ByteArrayOutputStream();
				DataOutputStream dos	= new DataOutputStream(baos);
				binMsg.write(dos);
				dos.flush();
				return baos.toByteArray();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new byte[0];

	}

}
