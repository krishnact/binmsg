package org.himalay.msgs.runtime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IBinMsg {
	abstract public int read(DataInputStream dis) throws IOException;

	abstract public int write(DataOutputStream dis) throws IOException;

	abstract public int dump(DumpContext dc) throws IOException;

}
