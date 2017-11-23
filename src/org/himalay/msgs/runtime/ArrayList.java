package org.himalay.msgs.runtime;

public class ArrayList<E> extends java.util.ArrayList<E> implements
		ComplexBinMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8862104225482807477L;

	private int memberSize = 0;

	public int getSize() {
		int retVal = 0;
		if (memberSize > 0) {
			retVal = getCount() * memberSize;
		} else if (getCount() == 0) {
			retVal = 0;
		} else if (this.get(0) instanceof ComplexBinMessage) {
			for (E obj : this) {
				retVal += ((ComplexBinMessage) obj).getSize();
			}
		}else if (this.get(0) instanceof BinStruct) {
			for (E obj : this) {
				try{
					retVal += ((BinStruct) obj).getSize();
				}catch(Exception e){}
			}
		}

		return retVal;
	}

	public int getCount() {
		return size();
	}

	public void setMemberSize(int memberSize) {
		this.memberSize = memberSize;
	}
}
