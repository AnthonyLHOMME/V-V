package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBooleanLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTWhileStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * Created by anthony on 13/10/14.
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