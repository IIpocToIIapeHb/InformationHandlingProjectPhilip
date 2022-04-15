package epam.com.infohandling;

public class ParagraphParser extends AbstractParser{

    public ParagraphParser(Parser successor) {
        super(successor);
    }
    public ParagraphParser() {
    }

    @Override
    public Component parse(String paragraph) {
        Composite composite = new Composite();
        String[] parts = paragraph.trim().split("(\\.{1,3}|\\?|!)");
        for (String part:parts){
            Component sentence = getSuccessor().parse(part);
            composite.add(sentence);
        }
        return composite;
    }
}
