/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.CobolParser.ArgumentContext;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.Argument;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;

public class ArgumentImpl extends CobolDivisionElementImpl implements Argument {

	protected ArgumentContext ctx;
	protected ArithmeticValueStmt arithmeticExpression;
	protected Call call;
	protected Literal literal;

	public ArgumentImpl(final ProgramUnit programUnit, final ArgumentContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		if(ctx.arithmeticExpression() != null){
			arithmeticExpression = createArithmeticValueStmt(ctx.arithmeticExpression());
		}

		if(ctx.identifier() != null){
			call = createCall(ctx.identifier());
		}

		if(ctx.literal() != null){
			literal = createLiteral(ctx.literal());
		}

		if(ctx.indexName() != null) {

			if (ctx.integerLiteral() != null) {
//				createIntegerLiteral(ctx.integerLiteral())
			}
		}
	}
}
