/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.call.impl;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;

import java.util.ArrayList;
import java.util.List;

public class DataDescriptionEntryCallImpl extends CallImpl implements DataDescriptionEntryCall {

	protected final CallType callType = CallType.DATA_DESCRIPTION_ENTRY_CALL;

	protected DataDescriptionEntry dataDescriptionEntry;
	protected List<CobolDivisionElementImpl> qualifiedInData;

	public DataDescriptionEntryCallImpl(final String name, final DataDescriptionEntry dataDescriptionEntry,
			final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(name, programUnit, ctx);

		this.dataDescriptionEntry = dataDescriptionEntry;
		if(ctx instanceof CobolParser.QualifiedDataNameFormat1Context){
			qualifiedInData = createQualifiedInData(programUnit, (CobolParser.QualifiedDataNameFormat1Context)ctx);
		}
	}

	private List<CobolDivisionElementImpl> createQualifiedInData(ProgramUnit programUnit, CobolParser.QualifiedDataNameFormat1Context ctx) {
		List<CobolDivisionElementImpl> ret = null;
		List<CobolParser.QualifiedInDataContext> qualifiedInDataContexts = ctx.qualifiedInData();
		if(  qualifiedInDataContexts != null && qualifiedInDataContexts.size() != 0) {
			ret = new ArrayList<>();
			for(CobolParser.QualifiedInDataContext context:qualifiedInDataContexts) {
				if(context.inTable() != null) {
					QualifiedInTable data = new QualifiedInTable(programUnit, context.inTable());
					ret.add(data);
				}else if(context.inData() != null){
					QualifiedInData data = new QualifiedInData(programUnit,context.inData());
					ret.add(data);
				}
			}
		}
		return ret;
	}

	@Override
	public CallType getCallType() {
		return callType;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntry() {
		return dataDescriptionEntry;
	}

	@Override
	public String toString() {
		return super.toString() + ", dataDescriptionEntry=[" + dataDescriptionEntry + "]";
	}
}
