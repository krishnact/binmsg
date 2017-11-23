package org.himalay.msgs.runtime;

import java.util.HashMap;
import java.util.Hashtable;

public abstract class Table {
//	public abstract String getMnemonic(int value);
//	public abstract String getDescription(int value);

	
	public static class Row
	{
		public String mnemonic;
		public String descrption;
		public Row(String mnemonic, String descrption) {
			super();
			this.mnemonic = mnemonic;
			this.descrption = descrption;
		}
	}
	
	public static class Range
	{
		public int startValue;
		public int endValue;
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + startValue;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Range other = (Range) obj;
			if ( (endValue >= other.startValue) &&  (startValue >= other.startValue))
				return true;
			else
				return false;
		}
		public Range(int startValue, int endValue) {
			super();
			this.startValue = startValue;
			this.endValue = endValue;
		}

	}
	public static Row findRow(Range range, Hashtable<Range, Row> rowsMap) {
		Row row	= rowsMap.get(range);
		return row;
	}

}
