package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtReturn;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtMethod;

public class TreeMethodProcessor extends AbstractProcessor<CtMethod> {

    private String nameEntryClass = "";

    @Override
    public void process(CtMethod e) {
        SourcePosition sp = e.getBody().getPosition();
        CompilationUnit compileUnit = sp.getCompilationUnit();

        // Fonction main du programme
        if (e.getSignature().equals("void main(java.lang.String[])")) {
            nameEntryClass = e.getDeclaringType().getSimpleName();

            // Declaration d'un conteur de tabulation pour la profondeur d'un appel d'une methode
            String nbTab = "\tpublic static int nbTab = 0;\n";
            SourceCodeFragment nbTab_code = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceStart()-1), nbTab, 0);
            compileUnit.addSourceCodeFragment(nbTab_code);
        }

        String nameMethod = e.getDeclaringType().getSimpleName()+"."+e.getSimpleName()+e.getParameters();

        // Ajout du code pour calculer la profondeur d'un appelle
        String snippet = "\t\tString tab = \"\";\n" +
                         "\t\tfor (int i_ = 0; i_ < "+nameEntryClass+".nbTab; i_++) {\n" +
                         "\t\t\ttab = tab+\" |\t\";\n" +
                         "\t\t}\n" +
                         "\t\tvv.spoon.logger.LogWriter.out(tab+\""+nameMethod+"\",false);\n" +
                         "\t\t"+nameEntryClass+".nbTab++;\n";
        SourceCodeFragment code = new SourceCodeFragment(compileUnit.nextLineIndex(sp.getSourceStart()), snippet, 0);
        compileUnit.addSourceCodeFragment(code);

        // Decrementation du nombre de tabulation à la sortie d'une methode
        String dec_string = "\t\t"+nameEntryClass+".nbTab--;\n";
        SourceCodeFragment dec_code;
        if (e.getBody().getLastStatement() instanceof CtReturn) {
            dec_code = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceEnd()-10), dec_string, 0);
        } else {
            dec_code = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceEnd()), dec_string, 0);
        }
        compileUnit.addSourceCodeFragment(dec_code);
    }
}
