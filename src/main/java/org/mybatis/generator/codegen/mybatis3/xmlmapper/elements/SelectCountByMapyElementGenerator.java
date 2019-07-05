//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.Iterator;

public class SelectCountByMapyElementGenerator extends AbstractXmlElementGenerator {
    public SelectCountByMapyElementGenerator() {
    }

    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", "selectCountByMap"));
        answer.addAttribute(new Attribute("parameterType", "java.util.Map"));
        answer.addAttribute(new Attribute("resultType", "java.lang.Integer"));
        this.context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("select count(0)  ");
        sb.append("from ");
        sb.append(this.introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        boolean and = false;
        Iterator var6 = this.introspectedTable.getAllColumns().iterator();
        while(var6.hasNext()) {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)var6.next();
            sb.setLength(0);
            if (!and) {
                sb.append(" where 1=1 ");
                and = true;
                answer.addElement(new TextElement(sb.toString()));
            }
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null and ");
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != ''");
            XmlElement isNotNullElement = new XmlElement("if");
            isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            answer.addElement(isNotNullElement);
            sb.setLength(0);
            sb.append(" and " + MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }

        if (this.context.getPlugins().sqlMapSelectByPrimaryKeyElementGenerated(answer, this.introspectedTable)) {
            parentElement.addElement(answer);
        }

    }
}
