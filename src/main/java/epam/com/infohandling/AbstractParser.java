package epam.com.infohandling;

public abstract class AbstractParser implements Parser {

    private Parser successor;

    public AbstractParser(Parser successor) {
        this.successor = successor;
    }
    public AbstractParser() {
    }

    protected Parser getSuccessor(){
        return successor;
    }
}
