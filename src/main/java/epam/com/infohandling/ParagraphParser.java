package epam.com.infohandling;

public class ParagraphParser extends AbstractParser{

    private final static String PARAGRAPH_DELIMITER = "(\\.{1,3}|\\?|!)";

    public ParagraphParser(Parser successor) {
        super(successor);
    }
    public ParagraphParser() {
    }

    @Override
    public Component parse(String paragraph) {
        Composite composite = new Composite();
        String[] parts = paragraph.trim().split(PARAGRAPH_DELIMITER);
        for (String part:parts){
            Component sentence = getSuccessor().parse(part);
            composite.add(sentence);
        }
        return composite;
    }
}
