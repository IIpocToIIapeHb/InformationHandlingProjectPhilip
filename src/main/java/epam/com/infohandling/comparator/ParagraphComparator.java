package epam.com.infohandling.comparator;

import epam.com.infohandling.Component;
import epam.com.infohandling.Composite;
import epam.com.infohandling.Lexeme;

import java.util.Comparator;
import java.util.List;

public class ParagraphComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        List<Component> sentencesFromParagraph1 = ((Composite) o1).getChildren();
        List<Component> sentencesFromParagraph2 = ((Composite) o2).getChildren();
        if (sentencesFromParagraph1.size() != sentencesFromParagraph2.size()) {
            return sentencesFromParagraph1.size() - sentencesFromParagraph2.size();
        } else {
            int lexemeNumbersOfParagraph1 = calculateLexemesNumberInParagraph(((Composite) o1));
            int lexemeNumbersOfParagraph2 = calculateLexemesNumberInParagraph(((Composite) o2));
            return lexemeNumbersOfParagraph1 - lexemeNumbersOfParagraph2;
        }
    }

    private int calculateLexemesNumberInParagraph(Composite paragraph) {
        int lexemeNumbersOfParagraph = 0;
        List<Component> sentences = paragraph.getChildren();
        for (Component sentence : sentences) {
            List<Component> lexemes = ((Composite) sentence).getChildren();
            for (Component componentlexeme : lexemes) {
                lexemeNumbersOfParagraph++;
            }
        }
        return lexemeNumbersOfParagraph;
    }
}


