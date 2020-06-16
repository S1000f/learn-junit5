package chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("@DisplayName testing")
public class LifecycleTest {

    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("set up");
    }

    @DisplayName("흐아아아아")
    @Test
    public void a() {
        System.out.println("A");
    }

    @Test
    public void b() {
        System.out.println("B");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("tearDown");
    }
}
