package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBooleanLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTBreakStatement;
import net.sourceforge.pmd.lang.java.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.java.ast.ASTWhileStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * Avoid while loops to be like while(false) {...} or while(true) {...}, with no break or
 * return in the repeated instructions of the while(true) {...}
 */
public class WhileLoopsNotTrueFalseBreakReturn extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {
        Node condition = node.jjtGetChild(0);
        ASTBooleanLiteral literalBoolCond = condition.getFirstDescendantOfType(ASTBooleanLiteral.class);
        if (literalBoolCond != null) {
            //ajout de la violation
            if (literalBoolCond.isTrue()) { // while(true) {...}
                Node whileIntrs = node.jjtGetChild(1);
                if (!whileIntrs.hasDescendantOfType(ASTBreakStatement.class)
                        && !whileIntrs.hasDescendantOfType(ASTReturnStatement.class)) { // No break and no return
                    addViolation(data, node);
                }
            } else { // while (false) {...}
                addViolation(data, node);
            }
        }
        return super.visit(node,data);
    }
}