package epam.com.infohandling.comparator;

import epam.com.infohandling.Component;
import epam.com.infohandling.Composite;

import java.util.Comparator;

public class ParagraphComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return ((Composite)o1).getChildren().size() -((Composite)o2).getChildren().size();
    }
}
