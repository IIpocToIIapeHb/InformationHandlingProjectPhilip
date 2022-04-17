package epam.com.infohandling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSentenceParser {

    private static final String SENTENCE = "There are [1200 5 /] sweets in the box.";

    @Test
    public void testParseShouldReturnCompositeOfLexemes() {
        //given
        Composite expectedComposite = new Composite();
        expectedComposite.add(Lexeme.word("There"));
        expectedComposite.add(Lexeme.word("are"));
        expectedComposite.add(Lexeme.expression("1200 5 /"));
        expectedComposite.add(Lexeme.word("sweets"));
        expectedComposite.add(Lexeme.word("in"));
        expectedComposite.add(Lexeme.word("the"));
        expectedComposite.add(Lexeme.word("box."));

        SentenceParser sentenceParser = new SentenceParser();
        //when
        Composite realComposite = (Composite) sentenceParser.parse(SENTENCE);
        //then
        assertEquals(expectedComposite,realComposite);
    }
}
