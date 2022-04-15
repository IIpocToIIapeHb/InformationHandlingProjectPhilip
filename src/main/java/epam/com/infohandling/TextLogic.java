package epam.com.infohandling;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class TextLogic {

    public Composite parse(String text){
        try {
            text = new String(Files.readAllBytes(Paths.get(text)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChainBuilder chainBuilder = new ChainBuilder();
        Parser textParser = chainBuilder.build();
        Composite textComposite = (Composite) textParser.parse(text);
        return textComposite;
    }

    public Composite calculate(Composite text, Map<String,Double> parameters){
        ExpressionCalculator calculator = new ExpressionCalculator();

        List<Component> paragraphs = text.getChildren();
        for (Component paragraph : paragraphs) {
            List<Component> sentances = ((Composite) paragraph).getChildren();
            for (Component sentence : sentances) {
                List<Component> lexemes = ((Composite) sentence).getChildren();
                for (Component componentlexeme : lexemes) {
                    Lexeme lexeme = (Lexeme) componentlexeme;
                    System.out.println(lexeme);
                    if (lexeme.getType() == LexemeType.EXPRESSION){
                        String expression = lexeme.getValue();
                        double resultOfExpression = calculator.calculate(expression,parameters);
                        String stringResultOfExpression = ((Double)resultOfExpression).toString();
                        lexeme.setValue(stringResultOfExpression);
                        lexeme.setType(LexemeType.WORD);
                    }

                }


            }
        }
        return text;
    }

}
