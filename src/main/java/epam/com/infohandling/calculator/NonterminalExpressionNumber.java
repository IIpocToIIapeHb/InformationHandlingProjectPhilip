package epam.com.infohandling.calculator;

public class NonterminalExpressionNumber extends AbstractMathExpression {

    private double number;
    public NonterminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
