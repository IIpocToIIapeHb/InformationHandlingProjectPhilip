package epam.com.infohandling.calculator;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {

    private ArrayList<AbstractMathExpression> listExpression = new ArrayList<>();
    private final static String EXPRESSION_DELIMITER = "\\s+";
    private final static String LITERAL_PARAMETERS = "(x|y)";

    public double calculate(String expression, Map<String, Integer> parameters) {
        listExpression.clear();
        parse(expression, parameters);
        Context context = new Context();
        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }


    private void parse(String expression, Map<String, Integer> parameters) {

        for (String lexeme : expression.split(EXPRESSION_DELIMITER)) {

            if (lexeme.isEmpty()) {
                continue;
            }

            if (lexeme.matches(LITERAL_PARAMETERS)) {
                int intLexeme = parameters.get(lexeme);
                lexeme = ((Integer) intLexeme).toString();
            }

            char temp = lexeme.charAt(0);
            switch (temp) {
                case '+':
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case '-':
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case '*':
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case '/':
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                default:
                    Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextDouble()) {
                        listExpression.add(new NonterminalExpressionNumber(scan.nextDouble()));
                    }
            }
        }
    }
}
