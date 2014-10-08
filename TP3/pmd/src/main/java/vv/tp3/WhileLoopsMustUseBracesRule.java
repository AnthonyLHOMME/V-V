package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class WhileLoopsMustUseBracesRule extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {
        Node firstStmt;
        firstStmt = (Node)node.jjtGetChild(1);
        if (!hasBlockAsFirstChild(firstStmt)) {
            //ajout de la violation
            addViolation(data, node);
        }
        return super.visit(node,data);
    }

    private boolean hasBlockAsFirstChild(Node node) {
        return (node.jjtGetNumChildren() != 0 && (node.jjtGetChild(0) instanceof ASTBlock));
    }
}