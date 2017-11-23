package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Created(date = "Mon Aug 27 16:23:07 EDT 2012")
public class UI8Header extends BinStruct {

	// members variables
	// messageType
	short messageType;

	public UI8Header() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize messageType

	}

	public int readNoHeader(DataInputStream istream) throws IOException {
		int retVal = 0;
		// read messageType
		{
			messageType = (short) (istream.readUnsignedByte());
			retVal += 1;
		}

		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		int retVal = 0;
		// read messageType
		{
			messageType = (short) (istream.readUnsignedByte());
			retVal += 1;
		}

		return retVal;
	}

	public int write(DataOutputStream ostream) throws IOException {

		int retVal = 0;
		// write messageType
		ostream.writeByte(messageType);
		retVal += 1;

		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {

//		dc.getPs().print("UI8Header\n");
		dc.increaseIndent();
		int retVal = 0;
		dc.indent();
		// write messageType
		dc.getPs().println(
				"type=" + messageType + "(0x"
						+ Integer.toHexString(messageType) + ")");
		dc.decreaseIndent();
		return retVal;
	}

	// Getter for messageType
	public short getMessageType() {
		return messageType;
	}

	// Setter for messageType
	public void setMessageType(short val) {
		this.messageType = val;
	}

	public int getSize() throws IOException {
		DataOutputStream dos = new DataOutputStream(new NullStream());
		return this.write(dos);
	}

}

// End of code