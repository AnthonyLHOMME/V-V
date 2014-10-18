package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBooleanLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTWhileStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * Avoid while loops to be like while(false) {...} or while(true) {...}
 */
public class WhileLoopsNotTrueFalse extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {
        Node condition = node.jjtGetChild(0);
        if (condition.hasDescendantOfType(ASTBooleanLiteral.class)) {
            //ajout de la violation
            addViolation(data, node);
        }
        return super.visit(node,data);
    }
}