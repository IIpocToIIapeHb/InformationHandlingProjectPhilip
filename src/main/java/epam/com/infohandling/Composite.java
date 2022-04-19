package epam.com.infohandling;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{
    private List<Component> children = new ArrayList<>();

    public void add(Component child){
        children.add(child);
    }

    public List<Component> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Composite composite = (Composite) o;

        if (!children.equals(composite.children)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return children.hashCode();
    }
}
