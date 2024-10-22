package io.proleap.cobol.asg.metamodel.call.impl;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.TableCall;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

/**
 * @author lidong@date 2024-10-22@version 1.0
 */
public class QualifiedInData extends CobolDivisionElementImpl {
    private Call call;
    private String type;
    public QualifiedInData(ProgramUnit programUnit, CobolParser.InDataContext ctx) {
        super(programUnit, ctx);
        type = ctx.IN() != null ? "IN" : "OF";
        call = new CallDelegateImpl(new CallDelegateImpl(createCall(ctx.dataName()),programUnit,ctx),programUnit,ctx);
    }

    public Call getCall() {
        return call;
    }

    public String getType() {
        return type;
    }
}
