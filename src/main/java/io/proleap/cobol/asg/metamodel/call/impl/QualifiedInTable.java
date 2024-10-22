package io.proleap.cobol.asg.metamodel.call.impl;

import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.TableCall;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

/**
 * @author lidong@date 2024-10-22@version 1.0
 */
public class QualifiedInTable extends CobolDivisionElementImpl {
    private Call call;
    private String type;
    public QualifiedInTable(ProgramUnit programUnit, CobolParser.InTableContext ctx) {
        super(programUnit, ctx);
        type = ctx.IN() != null ? "IN" : "OF";
        call = new CallDelegateImpl(createCall(ctx.tableCall()),programUnit,ctx);
    }

    public Call getCall() {
        return call;
    }

    public String getType() {
        return type;
    }
}
