package org.himalay.msgs.runtime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.DataInputStream;
public class DumpContext {
	public static DumpContext SYSTEM_OUT = new DumpContext();
	static int[] table1= {0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09};
	static int[] table2= {0x0a,0x0b,0x0c,0x0d,0x0e,0x0f};

	PrintStream ps;
	int indent;
	private String indentStr = "";
	private String indentStep = "  ";

	public void setIndentStep(String step)
	{
		this.indentStep	= step;
	}
	
	public String getIndentString() {
		return indentStr;
	}

	public PrintStream getPs() {
		return ps;
	}

	public void setPs(PrintStream ps) {
		this.ps = ps;
	}

	public void increaseIndent() {
		indent++;
		indentStr += indentStep;
	}

	public void decreaseIndent() {
		indent--;
		indentStr = indentStr.substring(indentStep.length());
	}

	public DumpContext(PrintStream ps) {
		super();
		this.ps = ps;
	}

	public DumpContext(String fileName) throws FileNotFoundException {
		this(new PrintStream(fileName));
	}
	
	public DumpContext(PrintStream ps, String indentStep) {
		super();
		this.ps = ps;
		this.indentStep = indentStep;
	}

	public DumpContext(OutputStream os) {
		this(new PrintStream(os));		
	}
	
	public DumpContext() {
		this(System.out);
	}

	public void indent() {
		ps.print(indentStr);
	}

	public void print(String string) {
		getPs().print(string);
	}
	
	public void println(String string) {
		getPs().println(string);
		indent();
	}

	public void write(byte[] b) throws IOException {
		ps.write(b);
	}

	public void flush() {
		ps.flush();
	}

	public void close() {
		ps.close();
	}

	public void write(int b) {
		ps.write(b);
	}

	public void write(byte[] buf, int off, int len) {
		ps.write(buf, off, len);
	}

	public void print(boolean b) {
		ps.print(b);
	}

	public void print(char c) {
		ps.print(c);
	}

	public void print(int i) {
		ps.print(i);
	}

	public void print(long l) {
		ps.print(l);
	}

	public void print(float f) {
		ps.print(f);
	}

	public void print(double d) {
		ps.print(d);
	}

	public void print(char[] s) {
		ps.print(s);
	}

	public void print(Object obj) {
		ps.print(obj);
	}

	public void println() {
		ps.println();
		indent();
	}

	public void println(boolean x) {
		ps.println(x);
		indent();
	}

	public void println(char x) {
		ps.println(x);
		indent();
	}

	public void println(int x) {
		ps.println(x);
		indent();
	}

	public void println(long x) {
		ps.println(x);
		indent();
	}

	public void println(float x) {
		ps.println(x);
		indent();
	}

	public void println(double x) {
		ps.println(x);
		indent();
	}

	public void println(char[] x) {
		ps.println(x);
		indent();
	}

	public void println(Object x) {
		ps.println(x);
		indent();
	}

	public static byte[] hexToBinary(String hex) {
	    String clean = hex.toUpperCase().replaceAll("0X","").replaceAll("[^0-9A-F]","");
	    int length = clean.length()/2;
	    byte[] retVal = new byte[length];
	    length = length*2;
	    byte[] bytes = clean.getBytes();
	   
	    for (int ii=0; ii < length; ) {
	    	byte aByte = bytes[ii];
	    	int thisByte = 0;
	    	if ( aByte <=58) {
	    		thisByte = table1[aByte-48];
	    	}else {
	    		thisByte = table2[aByte-65];
	    	}
	    	aByte = bytes[++ii];
	    	int nextByte = 0;
	    	if ( aByte < 58) {
	    		nextByte = table1[aByte-48];
	    	}else {
	    		nextByte = table2[aByte-65];
	    	}
	    	retVal[ii/2] = (byte)((thisByte<< 4)|nextByte);
	    	ii++;
	    }
	    
	    return retVal;
	}
	
	public static DataInputStream dataInputStream(String hexText){
		return new DataInputStream(new java.io.ByteArrayInputStream(hexToBinary(hexText)));
	}
}
