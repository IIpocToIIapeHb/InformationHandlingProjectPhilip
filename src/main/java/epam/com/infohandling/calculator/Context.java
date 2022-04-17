package epam.com.infohandling.calculator;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Double> contextValues = new ArrayDeque<>();

    public Double popValue() {
        return contextValues. removeLast();
    }
    public void pushValue(Double value) {
        this.contextValues.push(value);
    }
}
