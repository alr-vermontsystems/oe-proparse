/**
 * Value.java 
 * @author Peter Dalbadie
 * 21-Sep-2004
 * 
 * Copyright (c) 2004,2006 ProRefactor.org.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */


package org.prorefactor.treeparser;

/**
 * Represents objects that have a value.
 *
 */
public interface Value {

	public void setValue(Object fileName);

	public Object getValue();

}
