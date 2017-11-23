package org.himalay.msgs.runtime;

import java.io.IOException;
import java.io.OutputStream;

public class NullStream extends OutputStream {

	@Override
	public void write(int b) throws IOException {
		// Null stream so do not do anything
	}

	@Override
	public void write(byte[] b) throws IOException {
		// Null stream so do not do anything
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// Null stream so do not do anything
	}

	@Override
	public void flush() throws IOException {
		// Null stream so do not do anything
	}

	@Override
	public void close() throws IOException {
		// Null stream so do not do anything
	}

}
