package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addTest(){
        ArrayDeque<String> test=new ArrayDeque<String>();
        assertTrue("The deque should be empty.", test.isEmpty());
        test.addFirst("Impact");
        assertFalse("It should be not empty.", test.isEmpty());
        test.addLast("414");
        test.addFirst("Genshin");
        assertEquals(3,test.size());
        test.printDeque();

    }

}
