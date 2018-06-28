/** 19-Feb-07 by John Green
 * Copyright (C) 2007 Joanju Software. All rights reserved.
 */
package org.prorefactor.util;

import java.util.ArrayList;
import java.util.Iterator;


/** Base class for Tuples.
 * Credit for this class goes to: http://www.adventuresinsoftware.com/blog/?p=43
 */
public abstract class BaseTuple implements Comparable {

	// Ordered collection of elements.
	ArrayList<Object> elements = new ArrayList<Object>();

	// Strings used to display the tuple.
	String open;

	String separator;

	String close;

	// Initialize the strings for this tuple type.
	protected BaseTuple(String open, String separator, String close) {
		this.open = open;
		this.separator = separator;
		this.close = close;
	}

	// Add elements to the tuple. Supports dot-chaining.
	protected BaseTuple addElement(Object o) {
		elements.add(o);
		return this;
	}

	protected BaseTuple addElement(int i) {
		return addElement(new Integer(i));
	}

	// Compare two tuples. All elements must be equal.
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof BaseTuple))
			return false;
		BaseTuple that = (BaseTuple) obj;
		if (that.elements.size() != this.elements.size())
			return false;
		for (int i = 0; i < elements.size(); ++i) {
			if (!that.elements.get(i).equals(this.elements.get(i)))
				return false;
		}
		return true;
	}
	
	public Object getElement(int index) { return elements.get(index); }
	
	public Integer getIntegerElement(int index) { return (Integer)elements.get(index); }
	
	// Calculate a hash code based on the hash of each element.
	@Override
	public int hashCode() {
		int result = 0;
		Iterator it = elements.iterator();
		while (it.hasNext()) {
			result = result * 37 + it.next().hashCode();
		}
		return result;
	}

	// Display the tuple using the open, separator, and close
	// specified in the constructor.
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(open);
		Iterator it = elements.iterator();
		while (it.hasNext()) {
			result.append(it.next());
			if (it.hasNext())
				result.append(separator);
		}
		return result.append(close).toString();
	}

	// Order by the most significant element first.
	// The tuples must agree in size and type.
	@SuppressWarnings("unchecked")
	public int compareTo(Object o) {
		BaseTuple that = (BaseTuple) o;
		for (int i = 0; i < elements.size(); ++i) {
			int compare = ((Comparable) this.elements.get(i))
					.compareTo((Comparable) that.elements.get(i));
			if (compare != 0)
				return compare;
		}
		return 0;
	}


}
