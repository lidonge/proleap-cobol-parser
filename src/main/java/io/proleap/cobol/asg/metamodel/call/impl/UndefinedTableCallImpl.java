/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package io.proleap.cobol.asg.metamodel.call.impl;

import io.proleap.cobol.CobolParser.TableCallContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

/**
 * An unresolved call which retains the table-call syntax collected by the parser.
 */
public class UndefinedTableCallImpl extends TableCallImpl {

	public UndefinedTableCallImpl(final String name, final ProgramUnit programUnit, final TableCallContext ctx) {
		super(name, null, programUnit, ctx);
	}

	@Override
	public CallType getCallType() {
		return CallType.UNDEFINED_CALL;
	}
}
