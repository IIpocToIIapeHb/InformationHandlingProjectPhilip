package epam.com.infohandling;

public class SentenceParser extends AbstractParser {

    public SentenceParser() {
    }


    @Override
    public Component parse(String sentense) {
        Composite composite = new Composite();
        String[] parts = sentense.split("(\\[|\\])");

        for (int i=0; i<parts.length; i++) {

            if (i % 2 == 0) {

                String[] words = parts[i].trim().split("\\s");

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
