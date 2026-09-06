package io.proleap.cobol.asg.call;

import io.proleap.cobol.CobolBaseListener;
import io.proleap.cobol.CobolLexer;
import io.proleap.cobol.CobolParser;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class FunctionIndexedArgumentTest {
    @Test
    public void keepsIndexedFieldInOneArgument() throws Exception {
        File source = new File("src/test/resources/io/proleap/cobol/asg/call/MINFUNCTIONINDEX.cbl");
        var params = new CobolParserParamsImpl();
        params.setFormat(CobolSourceFormatEnum.FIXED);
        String text = new CobolPreprocessorImpl().process(source, params);
        CobolParser parser = new CobolParser(new CommonTokenStream(new CobolLexer(CharStreams.fromString(text))));
        int[] count = {0};
        ParseTreeWalker.DEFAULT.walk(new CobolBaseListener() {
            @Override public void enterFunctionCall(CobolParser.FunctionCallContext call) {
                count[0]++;
                assertEquals(2, call.argument().size());
                assertEquals("INPUT-AMT(ITEM-INDEX)", call.argument(0).getText());
                assertEquals("5", call.argument(1).getText());
            }
        }, parser.startRule());
        assertEquals(0, parser.getNumberOfSyntaxErrors());
        assertEquals(1, count[0]);
    }
}
