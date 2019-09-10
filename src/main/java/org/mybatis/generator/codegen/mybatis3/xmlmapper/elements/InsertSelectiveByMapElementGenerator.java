//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import java.util.Iterator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.GeneratedKey;

public class InsertSelectiveByMapElementGenerator extends AbstractXmlElementGenerator {
    public InsertSelectiveByMapElementGenerator() {
    }

    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("insert");
        answer.addAttribute(new Attribute("id", "insertSelectiveByMap"));
        FullyQualifiedJavaType parameterType = this.introspectedTable.getRules().calculateAllFieldsClass();
        answer.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        this.context.getCommentGenerator().addComment(answer);
        GeneratedKey gk = this.introspectedTable.getGeneratedKey();
        if (gk != null) {
            IntrospectedColumn introspectedColumn = this.introspectedTable.getColumn(gk.getColumn());
            if (introspectedColumn != null) {
                if (gk.isJdbcStandard()) {
                    answer.addAttribute(new Attribute("useGeneratedKeys", "true"));
                    answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
                    answer.addAttribute(new Attribute("keyColumn", introspectedColumn.getActualColumnName()));
                } else {
                    answer.addElement(this.getSelectKey(introspectedColumn, gk));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement insertTrimElement = new XmlElement("trim");
        insertTrimElement.addAttribute(new Attribute("prefix", "("));
        insertTrimElement.addAttribute(new Attribute("suffix", ")"));
        insertTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
        answer.addElement(insertTrimElement);
        XmlElement valuesTrimElement = new XmlElement("trim");
        valuesTrimElement.addAttribute(new Attribute("prefix", "values ("));
        valuesTrimElement.addAttribute(new Attribute("suffix", ")"));
        valuesTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
        answer.addElement(valuesTrimElement);
        Iterator var8 = ListUtilities.removeIdentityAndGeneratedAlwaysColumns(this.introspectedTable.getAllColumns()).iterator();

        while(true) {
            while(var8.hasNext()) {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)var8.next();
                if (!introspectedColumn.isSequenceColumn() && !introspectedColumn.getFullyQualifiedJavaType().isPrimitive()) {
                    sb.setLength(0);
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(" != null and ");
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(" != ''");
                    XmlElement insertNotNullElement = new XmlElement("if");
                    insertNotNullElement.addAttribute(new Attribute("test", sb.toString()));
                    sb.setLength(0);
                    sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
                    sb.append(',');
                    insertNotNullElement.addElement(new TextElement(sb.toString()));
                    insertTrimElement.addElement(insertNotNullElement);
                    sb.setLength(0);
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(" != null and ");
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append(" != ''");
                    XmlElement valuesNotNullElement = new XmlElement("if");
                    valuesNotNullElement.addAttribute(new Attribute("test", sb.toString()));
                    sb.setLength(0);
                    sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
                    sb.append(',');
                    valuesNotNullElement.addElement(new TextElement(sb.toString()));
                    valuesTrimElement.addElement(valuesNotNullElement);
                } else {
                    sb.setLength(0);
                    sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
                    sb.append(',');
                    insertTrimElement.addElement(new TextElement(sb.toString()));
                    sb.setLength(0);
                    sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
                    sb.append(',');
                    valuesTrimElement.addElement(new TextElement(sb.toString()));
                }
            }

            if (this.context.getPlugins().sqlMapInsertSelectiveElementGenerated(answer, this.introspectedTable)) {
                parentElement.addElement(answer);
            }

            return;
        }
    }
}
