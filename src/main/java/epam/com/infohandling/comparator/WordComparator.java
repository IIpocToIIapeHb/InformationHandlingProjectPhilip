package epam.com.infohandling.comparator;

import epam.com.infohandling.Component;
import epam.com.infohandling.Lexeme;

import java.util.Comparator;

public class WordComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        String lexeme1= ((Lexeme)o1).getValue();
        String lexeme2= ((Lexeme)o2).getValue();
        if (lexeme1.length()!=lexeme2.length()) {
            return lexeme1.length()-lexeme2.length();
        }
        return lexeme1.compareTo(lexeme2);
    }
}
