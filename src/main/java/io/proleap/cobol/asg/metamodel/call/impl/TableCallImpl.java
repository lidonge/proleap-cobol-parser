/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.CobolParser.SubscriptContext;
import io.proleap.cobol.CobolParser.TableCallContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.TableCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.SubscriptImpl;

public class TableCallImpl extends DataDescriptionEntryCallImpl implements TableCall {

	protected final CallType callType = CallType.TABLE_CALL;

	protected final TableCallContext ctx;

	protected List<Subscript> subscripts = new ArrayList<Subscript>();
	protected ReferenceModifier referenceModifier;

	public TableCallImpl(final String name, final DataDescriptionEntry dataDescriptionEntry,
			final ProgramUnit programUnit, final TableCallContext ctx) {
		super(name, dataDescriptionEntry, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Subscript addSubscript(final SubscriptContext ctx) {
		Subscript result = (Subscript) getASGElement(ctx);

		if (result == null) {
			result = new SubscriptImpl(programUnit, ctx);
			final ValueStmt subscriptValueStmt = createSubscriptValueStmt(ctx.integerLiteral(),
					ctx.qualifiedDataName(), ctx.indexName(), ctx.arithmeticExpression());
			final String subscriptText = ctx.getText();

			if (!subscripts.isEmpty() && subscriptText != null && !subscriptText.isEmpty()
					&& (subscriptText.charAt(0) == '+' || subscriptText.charAt(0) == '-')) {
				final Subscript previousSubscript = subscripts.get(subscripts.size() - 1);
				final ValueStmt mergedSubscriptValueStmt = createSignedOffsetValueStmt(
						previousSubscript.getSubscriptValueStmt(), subscriptText);
				previousSubscript.setSubscriptValueStmt(mergedSubscriptValueStmt);
				result.setSubscriptValueStmt(mergedSubscriptValueStmt);
				registerASGElement(result);

				return result;
			}

			result.setSubscriptValueStmt(subscriptValueStmt);

			subscripts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public TableCallContext getCtx() {
		return ctx;
	}

	@Override
	public List<Subscript> getSubscripts() {
		return subscripts;
	}

	@Override
	public ReferenceModifier createReferenceModifier(ProgramUnit programUnit,
													 CobolParser.ReferenceModifierContext referenceModifierContext) {
		this.referenceModifier = new ReferenceModifier(programUnit,referenceModifierContext);
		return referenceModifier;
	}

	@Override
	public ReferenceModifier getReferenceModifier() {
		return referenceModifier;
	}
}
