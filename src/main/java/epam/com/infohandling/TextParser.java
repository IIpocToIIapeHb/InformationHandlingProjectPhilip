package epam.com.infohandling;

public class TextParser extends AbstractParser {

    private final static String TEXT_DELIMITER = "\n";

    public TextParser(Parser successor) {
        super(successor);
    }
    public TextParser() {
    }

    @Override
    public Component parse(String text) {
        Composite composite = new Composite();
        String[] parts = text.split(TEXT_DELIMITER);
        for (String part:parts){
        Component paragraph = getSuccessor().parse(part);
        composite.add(paragraph);
        }
        return composite;
    }
}
