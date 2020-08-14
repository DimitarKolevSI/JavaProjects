package implementations;

public interface StackInt<X> {

    void push(X newElement);
    X pop();
    boolean contains(X item);
    X access(X item);
    int size();
}
