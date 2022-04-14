package epam.com.infohandling;

public class ChainBuilder {

    public Parser build(){
        return new TextParser(new ParagraphParser(null));
    }
}
