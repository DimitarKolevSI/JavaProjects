package implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ListStack<T>
{
    private List<T> elements;
    private int stackPosition;

    public ListStack() {
        this.elements =  new ArrayList<>(100);
        this.stackPosition = 0;
    }

    public void push(T newElement) {
        elements.add(stackPosition++,newElement);
    }

    public T pop() {
        if(stackPosition == 0)
        {
            throw new IllegalStateException("Stack is empty");
        }
        return elements.get(--stackPosition);
    }

    public boolean contains(T item) {
        return elements.contains(item);
    }

    public T access(T item) {
        while (stackPosition > 0)
        {
            T temp = pop();
            if( temp.equals(item))
            {
                return temp;
            }
        }
        throw new IllegalArgumentException("No such element!");
    }

    public int size() {
        return stackPosition;
    }
}
