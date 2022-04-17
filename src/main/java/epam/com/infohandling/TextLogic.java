package epam.com.infohandling;

import epam.com.infohandling.calculator.ExpressionCalculator;
import epam.com.infohandling.comparator.ParagraphComparator;
import epam.com.infohandling.comparator.WordComparator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TextLogic {

    public Composite parse(String path) throws TextLogicException {
        String text=null;
        try {
            text = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new TextLogicException(e.getMessage(),e);
        }
        ChainBuilder chainBuilder = new ChainBuilder();
        Parser textParser = chainBuilder.build();
        Composite textComposite = (Composite) textParser.parse(text);
        return textComposite;
    }


    public Composite calculate(Composite text, Map<String,Integer> parameters){
        Composite newText = new Composite();

        List<Component> paragraphs = text.getChildren();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = ((Composite) paragraph).getChildren();

            Composite newParagraph = new Composite();
            for (Component sentence : sentences) {
                List<Component> lexemes = ((Composite) sentence).getChildren();

                Composite newSentence = new Composite();
                for (Component componentlexeme : lexemes) {
                    Lexeme lexeme = (Lexeme) componentlexeme;
                    if (lexeme.getType() == LexemeType.WORD){
                        Lexeme newLexeme = Lexeme.word(lexeme.getValue());
                        newSentence.add(newLexeme);
                    } else
                        if (lexeme.getType() == LexemeType.EXPRESSION){
                        Lexeme newLexeme = calculateLexemeExpressionIntoLexemeWord(lexeme,parameters);
                        newSentence.add(newLexeme);
                    }
                }
                newParagraph.add(newSentence);
            }
            newText.add(newParagraph);
        }
        return newText;
    }


    private Lexeme calculateLexemeExpressionIntoLexemeWord(Lexeme lexeme, Map<String,Integer> parameters){
        ExpressionCalculator calculator = new ExpressionCalculator();
        String expression = lexeme.getValue();
        double resultOfExpression = calculator.calculate(expression,parameters);
        String stringResultOfExpression = ((Double)resultOfExpression).toString();
        Lexeme newLexeme = Lexeme.word(stringResultOfExpression);
        return newLexeme;

    }

    public void sortByParagraphs(Composite text){
        List<Component> list=text.getChildren();
        ParagraphComparator comparator=new ParagraphComparator();
        Collections.sort(list,comparator);
    }

    public void sortByWords(Composite text) {
        WordComparator comparator = new WordComparator();

        List<Component> paragraphs = text.getChildren();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = ((Composite) paragraph).getChildren();
            for (Component sentence : sentences) {
                Collections.sort(((Composite)sentence).getChildren(), comparator);
            }
        }
    }

}
