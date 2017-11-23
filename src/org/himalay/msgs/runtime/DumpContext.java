package org.himalay.msgs.runtime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class DumpContext {
	public static DumpContext SYSTEM_OUT = new DumpContext();
	
	PrintStream ps;
	int indent;
	private String indentStr = "";
	private String indentStep = "  ";

	public void setIndetStep(String step)
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


}
