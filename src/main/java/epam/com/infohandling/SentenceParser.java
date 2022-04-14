package epam.com.infohandling;

public class SentenceParser extends AbstractParser {

    public SentenceParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String sentense) {
        Composite composite = new Composite();
        String[] parts = sentense.split("(\\[|\\])");
        for (int i=0; i<parts.length;i+=2){

            String[] words = parts[i].split("\\s");

            Component paragraph = getSuccessor().parse(part);
            composite.add(paragraph);
        }
        return composite;
    }
}
