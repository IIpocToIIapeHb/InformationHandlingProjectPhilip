package epam.com.infohandling;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TestTextParser {

    private static final String PATH = "src/test/resources/TestText.txt";

    private static final Composite PARAGRAPH = createParagraphComposite();
    private static final String FIRST_SENTENCE_LEXEME = "Hello,";
    private static final String SECOND_SENTENCE_LEXEME = "world";

    @Test
    public void testParseShouldReturnCompositeOfParagraphs() {
        //given
        String text=null;
        try {
            text = new String(Files.readAllBytes(Paths.get(PATH)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Composite expectedTextComposite =createExpectedTextComposite();

        TextParser textParserMock = Mockito.mock(TextParser.class);
        when(textParserMock.parse(anyString())).thenReturn(PARAGRAPH);

        ParagraphParser textParser = new ParagraphParser(textParserMock);
        //when
        Composite realTextComposite = (Composite) textParser.parse(text);
        //then
        assertEquals(expectedTextComposite,realTextComposite);
    }


    private static Composite createParagraphComposite(){
        Composite paragraph = new Composite();

        Composite sentence = new Composite();
        sentence.add(Lexeme.word("FIRST_SENTENCE_LEXEME"));
        sentence.add(Lexeme.word("SECOND_SENTENCE_LEXEME"));

        paragraph.add(sentence);

        return paragraph;
    }

    private static Composite createExpectedTextComposite(){
        Composite expectedTextComposite = new Composite();

        Composite firstParagraph = createParagraphComposite();
        Composite secondParagraph = createParagraphComposite();
        Composite thirdParagraph = createParagraphComposite();

        expectedTextComposite.add(firstParagraph);
        expectedTextComposite.add(secondParagraph);
        expectedTextComposite.add(thirdParagraph);

        return expectedTextComposite;
    }
}
