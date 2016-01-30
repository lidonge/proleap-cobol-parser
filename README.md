cobol85grammar
==================================================

Cobol 85 Grammar for ANTLR4

This is an approximate grammar for Cobol 85. It is akin but neither 
copied from nor identical to Cobol.jj, Cobol.kg and VS COBOL II grammars.
Tested against the NIST test suite.


Characteristics:

1. Passes the NIST tests.

2. To be used in conjunction with the provided preprocessor, which executes 
   COPY and REPLACE statements.


Known limitations (work under progress):

1. Picture strings are parsed as (groups of) terminal symbols.

2. Comments are skipped.


Build process:

* The build process is based on Maven.
* The test suite executes tests against NIST, CICS and some GPLed Cobol test files.
* NIST test files come from [Koopa repo](https://github.com/goblindegook/Koopa/tree/master/testsuite/cobol85).


Release process:

* Milestones are published in the [ANTLR grammars repo](https://github.com/antlr/grammars-v4).


VM Args

* For parsing large Cobol source code files,  VM args have to be set: -Xmx2048m -XX:MaxPermSize=256m
* Intellij Plugin for ANTLR 4 has to be provided with those VM args in file idea.vmoptions.