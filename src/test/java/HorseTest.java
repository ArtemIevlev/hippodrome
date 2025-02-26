import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;



public class HorseTest {
    private Horse horse;

    @Test
    public void getHorse(){
        assertEquals("Name cannot be null.",
                assertThrows(IllegalArgumentException.class, () -> horse = new Horse(null, 20)).getMessage());
        assertEquals("Name cannot be blank.",
                assertThrows(IllegalArgumentException.class,() -> horse = new Horse("", 10)).getMessage());
        assertEquals("Name cannot be blank.",
                assertThrows(IllegalArgumentException.class,() -> horse = new Horse("\t", 10)).getMessage());
        assertEquals("Name cannot be blank.",
                assertThrows(IllegalArgumentException.class,() -> horse = new Horse("\n", 15)).getMessage());
        assertEquals("Speed cannot be negative.",
                assertThrows(IllegalArgumentException.class, ()-> horse = new Horse("Лошадка", -1)).getMessage());
        assertEquals("Distance cannot be negative.",
                assertThrows(IllegalArgumentException.class, ()-> horse = new Horse("Лошадка", 2, -1)).getMessage());
    }
    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        horse = new Horse("Horse", 6, 5);
        Field field = Horse.class.getDeclaredField("name");
        field.setAccessible(true);
        String name = (String) field.get(horse);
        assertEquals(name, horse.getName());
    }
    @Test
    public void getSpeed() throws NoSuchFieldException, IllegalAccessException {
        horse = new Horse("Horse", 6, 5);
        Field field = Horse.class.getDeclaredField("speed");
        field.setAccessible(true);
        double speed = (double)field.get(horse);
        assertEquals(speed, horse.getSpeed());
    }
    @Test
    public void getDistance(){
        horse = new Horse("Horse", 6, 5);
        assertEquals(5, horse.getDistance());
        horse = new Horse("Horse", 2);
        assertEquals(0, horse.getDistance());
    }
    @ParameterizedTest
    @ValueSource (doubles = {0.3, 0.8, 0.5})
    public void move(double randomValue){
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)){
            horse = new Horse("Horse", 6, 2);
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            horse.move();
            assertEquals(2 + 6 * randomValue, horse.getDistance());
            mocked.verify(()-> Horse.getRandomDouble(0.2, 0.9));
           };
    }
}
