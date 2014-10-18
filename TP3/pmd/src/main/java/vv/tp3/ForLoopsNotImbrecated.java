package vv.tp3;

import net.sourceforge.pmd.lang.java.ast.ASTForStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class ForLoopsNotImbrecated extends AbstractJavaRule {

    public Object visit(ASTForStatement node, Object data) {
        if (node.hasDescendantOfType(ASTForStatement.class)) {
            //ajout de la violation
            addViolation(data, node);
        }
        return super.visit(node,data);
    }
}