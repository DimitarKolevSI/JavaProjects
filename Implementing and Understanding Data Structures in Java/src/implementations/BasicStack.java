package implementations;

public class BasicStack<X> implements StackInt {
    private X[] elements;
    //variable to point where to put the new element
    private int stackPointer;
    public BasicStack()
    {
        //Creating array of 1000 elements and casting to array of X
        elements = (X[]) new Object[1000];
        stackPointer = 0;
    }

    public void push(Object newElement)
    {
        //Set the element on the stackPointer position to newElement and increase
        //the position pointer
        elements[stackPointer++] = (X) newElement;
    }

    public X pop()
    {
        //Check if the stack is empty and if so throws and exception
        if(stackPointer == 0 )
        {
            throw new IllegalStateException("The stack is empty");
        }
        //Return the top element of the stack and decrease the pointer
        return elements[--stackPointer];
    }

    public boolean contains(Object item)
    {
        //Check linear to see if one of the items is equal to the item we are searching
        for (int i=0;i<stackPointer;i++){
            if (item.equals(elements[i]))
            {
                //If the element is found return true and exit from the function
                return true;
            }
        }
        //If the element was not found return false by default
        return false;
    }

    public X access(Object item)
    {
        //Looping while the stack is not empty
        while(stackPointer > 0)
        {
            X tmp = pop();
            if(tmp.equals(item))
            {
                return tmp;
            }
        }
        //If the item was not found
        throw new IllegalArgumentException("The item was not part of the stack!");
    }

    //A simple method to get the size of the stack
    public int size()
    {
        return this.stackPointer;
    }
}
