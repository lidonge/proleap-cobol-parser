package io.proleap.cobol.asg.metamodel.call.impl;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ArithmeticValueStmtImpl;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @author lidong@date 2024-10-15@version 1.0
 */
public class ReferenceModifier extends CobolDivisionElementImpl {
    protected CobolParser.ReferenceModifierContext ctx;
    ArithmeticValueStmt characterPosition;
    ArithmeticValueStmt length;

    public ReferenceModifier(ProgramUnit programUnit, ParserRuleContext ctx) {
        super(programUnit, ctx);
        this.ctx = (CobolParser.ReferenceModifierContext) ctx;
        init();
    }

    private void init(){
        characterPosition = createArithmeticValueStmt(this.ctx.characterPosition().arithmeticExpression());
        if (ctx.length() != null){
            length = createArithmeticValueStmt(ctx.length().arithmeticExpression());
        }
    }

}
