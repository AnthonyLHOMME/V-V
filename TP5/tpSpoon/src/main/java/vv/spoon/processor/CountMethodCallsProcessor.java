package vv.spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtMethod;

import java.util.HashMap;
import java.util.Map;

public class CountMethodCallsProcessor extends AbstractProcessor<CtMethod> {

    private String nameEntryClass = "";
    public static Map<String, Integer> listMethod = new HashMap<String, Integer>();


    @Override
    public void process(CtMethod e) {
        SourcePosition sp = e.getBody().getPosition();
        CompilationUnit compileUnit = sp.getCompilationUnit();

        // Fonction main du programme
        if (e.getSignature().equals("void main(java.lang.String[])")) {
            nameEntryClass = e.getDeclaringType().getSimpleName();

            // Declaration des imports
            String import_string = "import java.util.HashMap;\nimport java.util.Map;\n";
            SourceCodeFragment import_code = new SourceCodeFragment(compileUnit.nextLineIndex(0), import_string, 0);
            compileUnit.addSourceCodeFragment(import_code);

            // Declaration de la map pour la sauvegarde (methode -> nombre d'appelle)
            String mapDecl = "\tpublic static Map<String, Integer> listMethod = new HashMap<String, Integer>();\n";
            SourceCodeFragment decl = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceStart()-1), mapDecl, 0);
            compileUnit.addSourceCodeFragment(decl);

            // Affichage des valeurs
            String log_string = "\t\tfor (String key: listMethod.keySet()) vv.spoon.logger.LogWriter.out(key+\": \"+listMethod.get(key),false);\n";
            SourceCodeFragment log_code = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceEnd()), log_string, 0);
            compileUnit.addSourceCodeFragment(log_code);
        }

        String nameMethod = e.getDeclaringType().getSimpleName()+"."+e.getSimpleName()+e.getParameters();

        // Ajout du code pour compter le nombre d'appelle de chaque fonction
        String snippet = "if ("+nameEntryClass+".listMethod.containsKey(\""+nameMethod+"\")) {\n" +
                         "   int value = "+nameEntryClass+".listMethod.get(\""+nameMethod+"\")+1;\n" +
                         "   "+nameEntryClass+".listMethod.put(\""+nameMethod+"\",value);\n" +
                         "} else {\n" +
                         "   "+nameEntryClass+".listMethod.put(\""+nameMethod+"\", 1);\n" +
                         "}\n";

        SourceCodeFragment code = new SourceCodeFragment(compileUnit.nextLineIndex(sp.getSourceStart()), snippet, 0);
        compileUnit.addSourceCodeFragment(code);
    }
}
