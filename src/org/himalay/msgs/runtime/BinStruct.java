package org.himalay.msgs.runtime;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public abstract class BinStruct extends BinPrimitive {
	abstract public int read(DataInputStream dis) throws IOException;

	abstract public int write(DataOutputStream dis) throws IOException;

	abstract public int dump(DumpContext dc) throws IOException;

	abstract public int getSize() throws IOException;
	/**
	 * This method will be called from write method just at the beginning of write method.
	 */
	public void preWrite()
	{
	}

	/**
	 * This method will be called from write method just at the end of write method.
	 */
	public void postWrite()
	{
	}

	/**
	 * This method will be called from write method just at the end of read and readNoHeader method.
	 */
	public void preRead()
	{
	}

	/**
	 * This method will be called from write method just at the end of read and readNoHeader method.
	 */
	public void postRead()
	{
	}
	
	/**
	 * This method is a general function that can be implemented in special cases to fix something in the objects
	 */
	public void generalFix(Object arg)
	{
	}
	
	public String toStringAdapter()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			this.dump(new DumpContext(baos));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(new PrintStream(baos));
		}
		return new String(baos.toByteArray());
	}
	
	public int dump() throws IOException
	{
		return this.dump(new DumpContext());
	}
}
