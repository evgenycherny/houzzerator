package com.e3.houzzerator.installcert.download;

import com.e3.houzzerator.installcert.download.model.ISubstitutor;

/**
 * Author:  etshiorny
 * Date:    7/2/16.
 */
public class DefaultSubstitutor implements ISubstitutor {
    private ScanContext context;
    private static final String EXPRESSION_START_MARKER = "${";
    private static final String EXPRESSION_END_MARKER = "}";

    public DefaultSubstitutor() {
        this.context = new ScanContext();
    }
    public DefaultSubstitutor(ScanContext context) {
        this.context = context;
    }

    public String substitute(String str) {
        String tempStr = str;
        while (hasMoreExpressions(tempStr)) {
            String expr = extractExpression(tempStr);
            String variable = extractVariableName(expr);

            if (!context.containsKey(variable))
                throw new MissingSubstitutionParameterException(
                        String.format("Parameter %s of string %s not found in context", variable, str));
            tempStr = tempStr.replace(expr, context.get(variable));
        }
        return tempStr;
    }

    private boolean hasMoreExpressions(String tempStr) {
        return tempStr.contains(EXPRESSION_START_MARKER);
    }

    private String extractExpression(String str) {
        int posStart = str.indexOf(EXPRESSION_START_MARKER);
        int posEnd = str.indexOf(EXPRESSION_END_MARKER, posStart);
        return str.substring(posStart, posEnd + 1);
    }
    private String extractVariableName(String expr) {
        return expr.substring(2, expr.length() - 1);
    }
}
