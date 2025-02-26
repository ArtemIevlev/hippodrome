import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    private Hippodrome hippodrome;

    @Test
    public void getHippodrome(){
        assertEquals("Horses cannot be null.",
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null)).getMessage());
        assertEquals("Horses cannot be empty.",
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList())).getMessage());
    }

    @Test
    public void getHorses(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Horse"+i, 1+i, 2+i/2));
        }
        hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }
    @Test
    public void move(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }

        new Hippodrome(horseList).move();

        for (Horse horse : horseList) {
            Mockito.verify(horse).move();
        }
    }
}
