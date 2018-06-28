/**
 * SemanticRecord.java
 * @author Peter Dalbadie
 * 21-Sep-2004
 * 
 * Copyright (c) 2004-2007 ProRefactor.org.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.prorefactor.treeparser;

import org.prorefactor.core.JPNode;

/**
 * Represents a record used in semantic processing.
 * It is a base class for more specific semantic records, which can
 * be definitions that appear in the SymbolTable, references
 * to previously defined items or other things of semantic
 * significance.
 * 
 * @author pcd
 *
 */
public class SemanticRecord {

	protected JPNode node;
	
	public SemanticRecord(){
		node = null;
	}
	
	public SemanticRecord(JPNode node){
		this.node = node;
	}


	/**
	 * @return
	 */
	public int getColumn() {
		return node.getColumn();
	}

	/**
	 * @return
	 */
	public String getFilename() {
		return node.getFilename();
	}

	/**
	 * @return
	 */
	public int getLine() {
		return node.getLine();
	}

}
