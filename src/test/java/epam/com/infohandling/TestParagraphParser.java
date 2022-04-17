package epam.com.infohandling;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestParagraphParser {


    private static final String PARAGRAPH = "There are [1200 5 /] sweets in the box. " +
            "Can you give me one of them? Yes, of course!";

    @Test
    public void testParseShouldReturnCompositeOfLexemes() {
        //given
        Composite expectedComposite = new Composite();

        Composite firstSentence = new Composite();
        firstSentence.add(Lexeme.word("There"));
        firstSentence.add(Lexeme.word("are"));
        firstSentence.add(Lexeme.expression("1200 5 /"));
        firstSentence.add(Lexeme.word("sweets"));
        firstSentence.add(Lexeme.word("in"));
        firstSentence.add(Lexeme.word("the"));
        firstSentence.add(Lexeme.word("box"));
        expectedComposite.add(firstSentence);

        Composite secondSentence = new Composite();
        secondSentence.add(Lexeme.word("Can"));
        secondSentence.add(Lexeme.word("you"));
        secondSentence.add(Lexeme.expression("give"));
        secondSentence.add(Lexeme.word("me"));
        secondSentence.add(Lexeme.word("one"));
        secondSentence.add(Lexeme.word("of"));
        secondSentence.add(Lexeme.word("them"));
        expectedComposite.add(secondSentence);

        Composite thirdSentence = new Composite();
        thirdSentence.add(Lexeme.word("Yes,"));
        thirdSentence.add(Lexeme.word("of"));
        thirdSentence.add(Lexeme.expression("course"));
        expectedComposite.add(thirdSentence);

        ParagraphParser paragraphParser = new ParagraphParser(new SentenceParser());
        //when
        Composite realComposite = (Composite) paragraphParser.parse(PARAGRAPH);
        //then
        assertEquals(expectedComposite,realComposite);
    }
}
