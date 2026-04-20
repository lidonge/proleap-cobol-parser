/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.impl;

import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivs;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PlusMinus;

public class SyntheticArithmeticValueStmtImpl extends ArithmeticValueStmtImpl {

	public SyntheticArithmeticValueStmtImpl(final ProgramUnit programUnit) {
		super(programUnit, null);
	}

	public SyntheticArithmeticValueStmtImpl getArithmeticExpression() {
		return this;
	}

	public void addSyntheticPlusMinus(final PlusMinus plusMinusValueStmt) {
		plusMinus.add(plusMinusValueStmt);
		addSubValueStmt(plusMinusValueStmt);
	}

	public void setMultDivs(final MultDivs multDivsValueStmt) {
		multDivs = multDivsValueStmt;
		addSubValueStmt(multDivsValueStmt);
	}
}
