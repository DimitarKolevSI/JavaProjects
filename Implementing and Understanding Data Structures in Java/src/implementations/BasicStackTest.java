package implementations;

import implementations.BasicStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.*;

public class BasicStackTest {

    BasicStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new BasicStack<>();
    }

    @Test
    public void testIfSizeWorksProperlyWhenOnlyAddingElements()
    {
        final int numberOfElements = 7;
        for (int i=0 ; i<numberOfElements ; i++)
        {
            stack.push(i);
        }
        assertEquals(numberOfElements,stack.size(),"Size should be " + numberOfElements);
    }

    @Test
    public void testIfSizeWorksProperlyWhenPushingAndPoppingElements()
    {
        final int numberOfElementsToAdd = 7;
        final int numberOfElementsToPop = 3;
        final int numberOfElementsExpected = numberOfElementsToAdd - numberOfElementsToPop;

        for (int i=0 ; i<numberOfElementsToAdd ; i++) stack.push(i);
        for (int i=0 ; i<numberOfElementsToPop ; i++) stack.pop();

        assertEquals(numberOfElementsExpected,stack.size(),"Size should be " + numberOfElementsExpected);
    }

    @Test
    public void testIfPushingElementsProperly()
    {
        final int numberOfElementsToPush = 10;
        int[] expectedArray = new int[numberOfElementsToPush];
        for(int i=0;i<numberOfElementsToPush;i++)
        {
            stack.push(i);
            expectedArray[i] = numberOfElementsToPush - 1 -i;
        }

        int[] elementsFromTheStack = new int[stack.size()];
        int index=0;
        while(stack.size() != 0)
        {
            elementsFromTheStack[index++] = stack.pop();
        }
        Assert.assertArrayEquals(expectedArray,elementsFromTheStack);
    }

    @Test
    public void testIfContainsWorksProperlyWhenGivenFalseElement()
    {
        final int numberOfElementsToPush = 10;
        final int elementToSearch = 12;

        for(int i=0;i<numberOfElementsToPush;i++) stack.push(i);

        Assert.assertFalse(stack.contains(elementToSearch));
    }
    @Test
    public void testIfContainsWorksProperlyWhenGivenAppropriateElement()
    {
        final int numberOfElementsToPush = 10;
        final int elementToSearch = 6;

        for(int i=0;i<numberOfElementsToPush;i++) stack.push(i);

        Assert.assertTrue(stack.contains(elementToSearch));
    }

    @Test
    public void testIfAccessWorksProperlyIfGivenExistingElement()
    {
        final int numberOfElementsToPush = 10;
        final int valueToAccess = 7;
        final int expectedSize = 7;
        for(int i=0;i<numberOfElementsToPush;i++) stack.push(i);

        int testedValue = stack.access(valueToAccess);

        assertEquals(valueToAccess,testedValue);
        assertEquals(expectedSize,stack.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAccessWorksProperlyIfGivenNonExistingElement()
    {
        final int numberOfElementsToPush = 10;
        final int valueToAccess = -2;
        for(int i=0;i<numberOfElementsToPush;i++) stack.push(i);

        int testedValue = stack.access(valueToAccess);
    }

    @Test(expected = IllegalStateException.class)
    public void testIfTryingToPopFromEmptyStack()
    {
        final int expectedSize = 0;
        assertEquals(expectedSize,stack.size());
        stack.pop();
    }
}