package schedulers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class PQTest {

    Comparator<Integer> comp =  new Comparator<>() {
        @Override
        public int compare(Integer o1, Integer o2) { return o1 - o2; }
    };
    PQ<Integer> pq;

    @BeforeEach
    void setUp() {
        pq = new PQ<>(comp);
    }

    @Test
    public void testPq() {
        assertNotNull(pq);
        assertTrue(pq.isEmpty());
        assertTrue(pq.offer(3));
        assertEquals(3, pq.peek());
        assertTrue(pq.offer(2));
        assertEquals(3, pq.peek());
        assertTrue(pq.offer(6));
        assertEquals(6, pq.peek());
        assertTrue(pq.offer(4));
        assertEquals(6, pq.peek());
        assertTrue(pq.offer(8));
        assertEquals(8, pq.peek());

        assertEquals(8, pq.poll());
        assertEquals(6, pq.peek());

        //TODO: Finish creating tests for other PQ methods
    }
}