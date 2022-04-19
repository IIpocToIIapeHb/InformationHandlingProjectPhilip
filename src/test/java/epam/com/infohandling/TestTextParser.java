package epam.com.infohandling;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TestTextParser {

    private static final String PATH = "src/test/resources/TestText.txt";

    @Test
    public void testParseShouldReturnCompositeOfLexemes() {
        //given
        String text=null;
        try {
            text = new String(Files.readAllBytes(Paths.get(PATH)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Composite expectedComposite = new Composite();

        Composite firstParagraph = new Composite();

        Composite firstSentence = new Composite();
        firstSentence.add(Lexeme.word("There"));
        firstSentence.add(Lexeme.word("are"));
        firstSentence.add(Lexeme.expression("1200 5 /"));
        firstSentence.add(Lexeme.word("sweets"));
        firstSentence.add(Lexeme.word("in"));
        firstSentence.add(Lexeme.word("the"));
        firstSentence.add(Lexeme.word("box"));
        firstParagraph.add(firstSentence);

        Composite secondSentence = new Composite();
        secondSentence.add(Lexeme.word("Can"));
        secondSentence.add(Lexeme.word("you"));
        secondSentence.add(Lexeme.expression("give"));
        secondSentence.add(Lexeme.word("me"));
        secondSentence.add(Lexeme.word("one"));
        secondSentence.add(Lexeme.word("of"));
        secondSentence.add(Lexeme.word("them"));
        firstParagraph.add(secondSentence);

        Composite thirdSentence = new Composite();
        thirdSentence.add(Lexeme.word("Yes,"));
        thirdSentence.add(Lexeme.word("of"));
        thirdSentence.add(Lexeme.expression("course"));
        firstParagraph.add(thirdSentence);

        expectedComposite.add(firstParagraph);

        Composite secondParagraph = new Composite();
        Composite sentence = new Composite();
        sentence.add(Lexeme.word("Bye"));
        secondParagraph.add(sentence);

        expectedComposite.add(secondParagraph);


        TextParser textParser = new TextParser(new ParagraphParser(new SentenceParser()));
        //when
        Composite realComposite = (Composite) textParser.parse(text);
        //then
        assertEquals(expectedComposite,realComposite);
    }
}
