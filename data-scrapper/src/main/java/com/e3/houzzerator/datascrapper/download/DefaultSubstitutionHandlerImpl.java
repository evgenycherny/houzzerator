package com.e3.houzzerator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.model.SubstitutionHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Author:  etshiorny
 * Date:    7/2/16.
 */
@Data
@Slf4j
public class DefaultSubstitutionHandlerImpl implements SubstitutionHandler {
    private ScanContext context;
    private static final String EXPRESSION_START_MARKER = "${";
    private static final String EXPRESSION_END_MARKER = "}";

    public DefaultSubstitutionHandlerImpl() {
        this.context = new ScanContext();
    }
    DefaultSubstitutionHandlerImpl(ScanContext context) {
        this.context = context;
    }

    public String substitute(String originalStr) {
        String result = originalStr;
        while (hasMoreExpressions(result)) {
            result = substituteExpression(originalStr, result);
        }
        return result;
    }

    private String substituteExpression(String originalStr, String result) {
        String expr = extractExpression(result);
        String variable = extractVariableName(expr);

        if (!context.containsKey(variable))
            throw new MissingSubstitutionParameterException(
                    String.format("Parameter %s of string %s not found in context", variable, originalStr));
        return result.replace(expr, context.get(variable));
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
