package ripley.banco.interview.technicaltest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TechnicalTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void smokeTestJunitWorks() {
        assertTrue(true);
    }

}
