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
        test.addFirst("mhyyyy");
        assertEquals("414",test.removeLast());
        assertEquals(3,test.size());
        test.addFirst("winwinwin");
        String rtv2=test.getFirst();
        String rtv3=test.getLast();
        assertEquals("winwinwin",rtv2);
        assertEquals("Impact",rtv3);
        test.addFirst("Tower");
        assertEquals("Tower",test.removeFirst());
        assertEquals(4,test.size());
        assertEquals("mhyyyy",test.get(1));
        test.printDeque();

    }

}
