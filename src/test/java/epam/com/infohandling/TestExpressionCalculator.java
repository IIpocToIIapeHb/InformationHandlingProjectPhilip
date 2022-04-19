package epam.com.infohandling;

import epam.com.infohandling.calculator.ExpressionCalculator;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TestExpressionCalculator {

    private final static String DIVIDE_EXPRESSION = "120 2 /";
    private final static double EXPECTED_DIVIDE_EXPRESSION_RESULT = 60;

    private final static String PLUS_EXPRESSION = "120 5 +";
    private final static double EXPECTED_PLUS_EXPRESSION_RESULT = 125;

    private final static String MULTIPLY_EXPRESSION = "120 5 *";
    private final static double EXPECTED_MULTIPLY_EXPRESSION_RESULT = 600;

    private final static String MINUS_EXPRESSION = "120 5 -";
    private final static double EXPECTED_MINUS_EXPRESSION_RESULT = 115;

    private final static String COMPLEX_EXPRESSION ="120 x * 8 + 4 / y -" ;
    private final static double EXPECTED_COMPLEX_EXPRESSION_RESULT = 60;

    private final static double DOUBLE_DELTA = 0.01;

    private final static Map<String,Integer> PARAMETERS = new HashMap<>();


    @Test
    public void testCalculateShouldReturnDivideExpressionResult(){
        //given
        /*Map<String,Integer> parameters = Mockito.mock(HashMap.class);
        when(parameters.get(anyString())).thenReturn(2);*/

        ExpressionCalculator calculator = new ExpressionCalculator();
        //when
        double realResult = calculator.calculate(DIVIDE_EXPRESSION,PARAMETERS);
        //then
        assertEquals(EXPECTED_DIVIDE_EXPRESSION_RESULT,realResult,DOUBLE_DELTA);
    }

    @Test
    public void testCalculateShouldReturnPlusExpressionResult(){
        //given
        ExpressionCalculator calculator = new ExpressionCalculator();
        //when
        double realResult = calculator.calculate(PLUS_EXPRESSION,PARAMETERS);
        //then
        assertEquals(EXPECTED_PLUS_EXPRESSION_RESULT,realResult,DOUBLE_DELTA);
    }

    @Test
    public void testCalculateShouldReturnMultiplyExpressionResult(){
        //given
        ExpressionCalculator calculator = new ExpressionCalculator();
        //when
        double realResult = calculator.calculate(MULTIPLY_EXPRESSION,PARAMETERS);
        //then
        assertEquals(EXPECTED_MULTIPLY_EXPRESSION_RESULT,realResult,DOUBLE_DELTA);
    }

    @Test
    public void testCalculateShouldReturnMinusExpressionResult(){
        //given
        ExpressionCalculator calculator = new ExpressionCalculator();
        //when
        double realResult = calculator.calculate(MINUS_EXPRESSION,PARAMETERS);
        //then
        assertEquals(EXPECTED_MINUS_EXPRESSION_RESULT,realResult,DOUBLE_DELTA);
    }

    @Test
    public void testCalculateShouldReturnComplexExpressionResult(){
        //given
        Map<String,Integer> parameters = new HashMap<>();
        parameters.put("x",2);
        parameters.put("y",2);

        ExpressionCalculator calculator = new ExpressionCalculator();
        //when
        double realResult = calculator.calculate(COMPLEX_EXPRESSION,parameters);
        //then
        assertEquals(EXPECTED_COMPLEX_EXPRESSION_RESULT,realResult,DOUBLE_DELTA);
    }
}
