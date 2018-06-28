/* PRCException.java
 * Created on Jun 3, 2004
 * John Green
 *
 * Copyright (C) 2004 Joanju Limited
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.prorefactor.core;

/** ProRefactor Core Exception */
public class PRCException extends Exception {

	public PRCException() {
		super();
	}

	public PRCException(String message) {
		super(message);
	}

	public PRCException(Throwable cause) {
		super(cause);
	}

	public PRCException(String message, Throwable cause) {
		super(message, cause);
	}

}
