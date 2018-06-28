/* MacroLevel.java
 * Created on Dec 17, 2003
 * John Green
 *
 * Copyright (C) 2003-2007 Joanju Software
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.prorefactor.macrolevel;

import java.util.ArrayList;


/** Static functions for working with an existing macro tree.
 */
public class MacroLevel {



	/** Trace back nested macro definitions until we find the original source.
	 * @return int[3] - file, line, column.
	 */
	public static int[] getDefinitionPosition(MacroDef def) {
		int[] ret = new int[3];
		if (def.includeRef==null) {
			if (def.parent instanceof NamedMacroRef) {
				return getDefinitionPosition(((NamedMacroRef)def.parent).macroDef);
			}
			ret[0] = ((IncludeRef)def.parent).fileIndex;
			ret[1] = def.line;
			ret[2] = def.column;
		} else {
			// Include arguments don't get their file/line/col stored, so
			// we have to find the include reference source.
			if (! (def.includeRef.parent instanceof IncludeRef))
				return getDefinitionPosition(((NamedMacroRef)def.includeRef.parent).macroDef);
			ret[0] = ((IncludeRef)def.includeRef.parent).fileIndex;
			ret[1] = def.includeRef.refLine;
			ret[2] = def.includeRef.refColumn;
		}
		return ret;
	}


	/** Build and return an array of the MacroRef objects, which would map to the SOURCENUM attribute from JPNode.
	 * Built simply by walking the tree and adding every MacroRef to the array.
	 */
	public static MacroRef [] sourceArray(MacroRef top) {
		ArrayList<MacroRef> list = new ArrayList<MacroRef>();
		sourceArray2(top, list);
		MacroRef [] ret = new MacroRef[list.size()];
		return list.toArray(ret);
	}
	private static void sourceArray2(MacroRef macroNode, ArrayList<MacroRef> list) {
		list.add(macroNode);
		for (MacroEvent event : ((MacroRef)macroNode).macroEventList) {
			if (event instanceof MacroRef) {
				sourceArray2((MacroRef)event, list);
			}
		}
	}


}
