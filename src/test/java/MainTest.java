import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(unit = TimeUnit.SECONDS, value = 22)
    @Disabled
    public void main(){
    }
}
