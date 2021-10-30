package com.metricalsky.backlogged.quality.pmd.jpa;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import net.sourceforge.pmd.lang.java.types.TypeTestUtil;
import org.springframework.data.domain.Persistable;

public class EntityShouldBePersistableRule extends AbstractJavaRule {

    public EntityShouldBePersistableRule() {
        addRuleChainVisit(ASTClassOrInterfaceDeclaration.class);
    }

    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        if (node.getAnnotation("javax.persistence.Entity") != null
                && !TypeTestUtil.isA(Persistable.class, node)) {
            addViolation(data, node);
        }
        return data;
    }
}
