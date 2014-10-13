#!/bin/bash

java -cp .:target/pmd-5.0.5-jar-with-dependencies.jar net.sourceforge.pmd.PMD -d $1 -R java-basic,java-design,java-unusedcode,java-optimizations,java-empty,java-strictexception,java-strings -f vbhtml -r report.html
