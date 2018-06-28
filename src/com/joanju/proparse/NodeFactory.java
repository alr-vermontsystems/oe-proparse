/*
NodeFactory.java

Copyright (C) 2001-2008 Joanju Software (www.joanju.com). All rights reserved.
This file is made available under the terms of the Eclipse Public License v1.0.
*/
package com.joanju.proparse;

import antlr.ASTFactory;
import antlr.Token;
import antlr.collections.AST;

import java.util.Hashtable;

import org.prorefactor.core.JPNode;
import org.prorefactor.nodetypes.BlockNode;
import org.prorefactor.nodetypes.FieldRefNode;
import org.prorefactor.nodetypes.ProgramRootNode;
import org.prorefactor.nodetypes.ProparseDirectiveNode;
import org.prorefactor.nodetypes.RecordNameNode;

public class NodeFactory extends ASTFactory {

	public NodeFactory(Hashtable hashtable, DoParse doParse) {
		super(hashtable);
		this.filenameList = doParse.getFilenameList();
	}

	private IntegerIndex<String> filenameList;



	@Override
	public AST create() {
		return new JPNode(new ProToken(filenameList, 0, ""));
	}

	/** Used for synthetic node creation by the Antlr generated parser. */
	@Override
	public AST create(int type) {
		ProToken token = new ProToken(filenameList, type, "");
		switch(type) {
			case NodeTypes.Field_ref:
				return new FieldRefNode(token);
			case NodeTypes.Program_root:
				return new ProgramRootNode(token);
			default:
				return new JPNode(token);
		}
	}

	@Override
	public AST create(int i, String s) {
		return new JPNode(new ProToken(filenameList, i, s));
	}


	/** Override Antlr's default use of the class name and reflection.
	 * Antlrs's call to its createUsingCtor() looks a bit expensive to
	 * me. Here, we're able to just use a switch on the token type,
	 * because that will always tell us which subclass to use. We're
	 * unlikely to ever want multiple node subclasses for any one
	 * token type.
	 * @param s Class name... is ignored here, but it is
	 * used in the generated parser code for casting the return.
	 */
	@Override
	public AST create(Token token, String s) {
		switch (token.getType()) {
			case NodeTypes.RECORD_NAME:
				return new RecordNameNode((ProToken)token);
			case NodeTypes.PROPARSEDIRECTIVE:
				return new ProparseDirectiveNode((ProToken)token);
			case NodeTypes.DO:
			case NodeTypes.FOR:
			case NodeTypes.REPEAT:
			case NodeTypes.FUNCTION:
			case NodeTypes.PROCEDURE:
			case NodeTypes.CONSTRUCTOR:
			case NodeTypes.DESTRUCTOR:
			case NodeTypes.METHOD:
			case NodeTypes.CANFIND:
			case NodeTypes.ON:
				return new BlockNode((ProToken)token);
			default:
				throw new IllegalArgumentException(
						"Proparse error creating AST node "
						+ token.toString()
						+ ", " + s
						);
		}
	}


	/** Used for restoring serialized JPNode objects. */
	public static JPNode createByIndex(int index) {
		switch (index) {
		case 1:
			return new JPNode();
		case 2:
			return new BlockNode();
		case 3:
			return new FieldRefNode();
		case 4:
			return new RecordNameNode();
		case 5:
			return new ProparseDirectiveNode();
		case 6:
			return new ProgramRootNode();
		default:
			throw new IllegalArgumentException();
		}
	}


}
