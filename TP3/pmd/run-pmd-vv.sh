#!/bin/sh

java -cp .:target/pmd-5.0.5-jar-with-dependencies.jar net.sourceforge.pmd.PMD -d $1 -R src/main/resources/rulesets/java/VVRulesSet.xml -f vbhtml -r report.html
