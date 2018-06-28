/* Created Apr, 2005
 * Authors: John Green
 * 
 * Copyright (c) 2005 Joanju (www.joanju.com)
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.prorefactor.nodetypes;

import org.prorefactor.core.JPNode;

import com.joanju.proparse.ProToken;
import com.joanju.DataXferStream;

import java.io.IOException;


public class ProparseDirectiveNode extends JPNode {

	/** For creating from persistent storage */
	public ProparseDirectiveNode() { super(); }

	public ProparseDirectiveNode(int file, int line, int column) { super(file, line, column); }

	public ProparseDirectiveNode(ProToken t) {
		super(t);
		directiveText = t.getText();
	}

	private String directiveText = "";

	/** Get the directive text. Might return empty, but should not return null. */
	public String getDirectiveText() {
		return directiveText;
	}

	/** Every JPNode subtype has its own index. Used for persistent storage. */
	@Override
	public int getSubtypeIndex() { return 5; }

	public void setDirectiveText(String text) { directiveText = text; }


	/** Implement Xferable. */
	@Override
	public void writeXferBytes(DataXferStream out) throws IOException {
		super.writeXferBytes(out);
		out.writeRef(directiveText);
	}

	/** Implement Xferable. */
	@Override
	public void writeXferSchema(DataXferStream out) throws IOException {
		super.writeXferSchema(out);
		out.schemaRef("directiveText");
	}


}
