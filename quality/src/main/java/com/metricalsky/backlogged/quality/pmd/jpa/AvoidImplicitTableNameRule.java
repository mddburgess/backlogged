package com.metricalsky.backlogged.quality.pmd.jpa;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMemberValuePairs;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class AvoidImplicitTableNameRule extends AbstractJavaRule {

    private static final String ENTITY = "javax.persistence.Entity";
    private static final String TABLE = "javax.persistence.Table";

    public AvoidImplicitTableNameRule() {
        addRuleChainVisit(ASTClassOrInterfaceDeclaration.class);
    }

    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        if (node.getAnnotation(ENTITY) == null) {
            return data;
        }

        var tableAnnotation = node.getAnnotation(TABLE);
        if (tableAnnotation == null) {
            var superclass = node.getSuperClassTypeNode();
            if (superclass == null) {
                addViolation(data, node);
                return data;
            }

            var type = superclass.getType();
            while (type != null) {
                for (var annotation : type.getAnnotations()) {
                    if (annotation.annotationType().getCanonicalName().equals(TABLE)) {
                        return data;
                    }
                }
                type = type.getSuperclass();
            }

            addViolation(data, node);
            return data;
        }

        var memberValues = tableAnnotation.getFirstDescendantOfType(ASTMemberValuePairs.class);
        if (memberValues == null) {
            addViolation(data, node);
            return data;
        }

        for (var memberValue : memberValues) {
            if (memberValue.getMemberName().equals("name")) {
                return data;
            }
        }

        addViolation(data, node);
        return data;
    }
}
