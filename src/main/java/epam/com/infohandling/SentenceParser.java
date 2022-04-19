package epam.com.infohandling;

public class SentenceParser extends AbstractParser {

    private final static String   EXPRESSIONS_DELIMITER_IN_SENTENCE = "(\\[|\\])";
    private final static String   WORDS_DELIMITER_IN_SENTENCE = "\\s";

    public SentenceParser() {
    }

    @Override
    public Component parse(String sentence) {
        Composite composite = new Composite();
        String[] parts = sentence.split(EXPRESSIONS_DELIMITER_IN_SENTENCE);
        for (int i=0; i<parts.length; i++) {

            if (i % 2 == 0) {

                String[] words = parts[i].trim().split(WORDS_DELIMITER_IN_SENTENCE);
                for (String parseWord : words) {
                    Lexeme wordLexeme = Lexeme.word(parseWord);
                    composite.add(wordLexeme);
                }

            } else {
                Lexeme expressionLexeme = Lexeme.expression(parts[i]);
                composite.add(expressionLexeme);
            }
        }

        return composite;
    }
}
