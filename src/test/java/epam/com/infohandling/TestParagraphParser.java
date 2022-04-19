package epam.com.infohandling;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TestParagraphParser {


    private static final String PARAGRAPH = "Hello, world! Hello, world. Hello, world?";

    private static final Composite SENTENCE = createSentenceComposite();
    private static final String FIRST_SENTENCE_LEXEME = "Hello,";
    private static final String SECOND_SENTENCE_LEXEME = "world";

    @Test
    public void testParseShouldReturnCompositeOfSentences() {
        //given
        Composite expectedParagraphComposite = createParagraphComposite();

        SentenceParser sentenceParserMock = Mockito.mock(SentenceParser.class);
        when(sentenceParserMock.parse(anyString())).thenReturn(SENTENCE);

        ParagraphParser paragraphParser = new ParagraphParser(sentenceParserMock);
        //when
        Composite realComposite = (Composite) paragraphParser.parse(PARAGRAPH);
        //then
        assertEquals(expectedParagraphComposite,realComposite);
    }

    private static Composite createSentenceComposite(){
        Composite sentence = new Composite();
        sentence.add(Lexeme.word("FIRST_SENTENCE_LEXEME"));
        sentence.add(Lexeme.word("SECOND_SENTENCE_LEXEME"));
        return sentence;
    }

    private static Composite createParagraphComposite(){
        Composite expectedParagraphComposite = new Composite();

        Composite firstSentence = createSentenceComposite();
        Composite secondSentence =  createSentenceComposite();
        Composite thirdSentence = createSentenceComposite();

        expectedParagraphComposite.add(firstSentence);
        expectedParagraphComposite.add(secondSentence);
        expectedParagraphComposite.add(thirdSentence);

        return expectedParagraphComposite;
    }
}
