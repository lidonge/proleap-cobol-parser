/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.metamodel.valuestmt.Argument;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ArgumentImpl;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.FunctionCall;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallImpl extends CallImpl implements FunctionCall {

	protected final CallType callType = CallType.FUNCTION_CALL;
	protected List<Argument> arguments;

	protected ReferenceModifier referenceModifier;

	public FunctionCallImpl(final String name, final ProgramUnit programUnit, final CobolParser.FunctionCallContext ctx) {
		super(name, programUnit, ctx);

		List<CobolParser.ArgumentContext> argument = ctx.argument();
		if(argument != null && argument.size() != 0){
			arguments = new ArrayList<>();
			for(CobolParser.ArgumentContext argumentContext:argument){
				arguments.add(new ArgumentImpl(programUnit,argumentContext));
			}
		}
		CobolParser.ReferenceModifierContext referenceModifierContext = ctx.referenceModifier();
		if(referenceModifierContext != null){
			referenceModifier = new ReferenceModifier(programUnit,referenceModifierContext);
		}
	}

	@Override
	public CallType getCallType() {
		return callType;
	}
}
