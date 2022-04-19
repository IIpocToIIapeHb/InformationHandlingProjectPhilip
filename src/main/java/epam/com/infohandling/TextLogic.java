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
        String text = null;
        try {
            text = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new TextLogicException(e.getMessage(), e);
        }
        ChainBuilder chainBuilder = new ChainBuilder();
        Parser textParser = chainBuilder.build();
        Composite textComposite = (Composite) textParser.parse(text);
        return textComposite;
    }


    public Composite calculate(Composite text, Map<String, Integer> parameters) {
        Composite newText = cloneTextComposite(text);

        List<Component> paragraphs = newText.getChildren();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = ((Composite) paragraph).getChildren();

            for (Component sentence : sentences) {
                List<Component> lexemes = ((Composite) sentence).getChildren();

                for (Component componentlexeme : lexemes) {
                    Lexeme lexeme = (Lexeme) componentlexeme;
                    if (lexeme.getType() == LexemeType.EXPRESSION) {
                        calculateLexemeExpressionIntoLexemeWord(lexeme, parameters);

                    }
                }

            }

        }
        return newText;
    }


    private void calculateLexemeExpressionIntoLexemeWord(Lexeme lexeme, Map<String, Integer> parameters) {
        ExpressionCalculator calculator = new ExpressionCalculator();
        String expression = lexeme.getValue();
        double resultOfExpression = calculator.calculate(expression, parameters);
        String stringResultOfExpression = ((Double) resultOfExpression).toString();
        lexeme.setType(LexemeType.WORD);
        lexeme.setValue(stringResultOfExpression);

    }

    public Composite sortByParagraphs(Composite text) {
        Composite newText = cloneTextComposite(text);
        List<Component> list = newText.getChildren();
        ParagraphComparator comparator = new ParagraphComparator();
        Collections.sort(list, comparator);
        return newText;
    }

    public Composite sortByWords(Composite text) {
        Composite newText = cloneTextComposite(text);
        WordComparator comparator = new WordComparator();
        List<Component> paragraphs = newText.getChildren();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = ((Composite) paragraph).getChildren();
            for (Component sentence : sentences) {
                Collections.sort(((Composite) sentence).getChildren(), comparator);
            }
        }
        return newText;
    }

    private Composite cloneTextComposite(Composite text) {
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

                    newSentence.add(lexeme.clone());
                }
                newParagraph.add(newSentence);
            }
            newText.add(newParagraph);
        }

        return newText;

    }

}
