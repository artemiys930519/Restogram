package app.repository;

import app.model.Restaurant;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

import static app.RestaurantTestData.*;

public class InMemoryRestaurantRepositoryTest {

    private static InMemoryRestaurantRepository repository;

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-15s %d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            System.out.println(result);
        }
    };

    @Before
    public void init() {
        repository = new InMemoryRestaurantRepository();
    }

    @Test
    public void getAllTest() {
        assertMatch(repository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);
    }

    @Test
    public void deleteTest() {
        repository.delete(RESTAURANT1.getId());
        assertMatch(repository.getAll(), RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);
    }

    @Test
    public void saveTest() {
        Restaurant result = getCreated();
        repository.save(result);
        assertMatch(repository.get(result.getId()), result);
    }

    @Test
    public void updateTest() {
        repository.save(getUpdated());
        assertMatch(repository.get(getUpdated().getId()),getUpdated());
    }
}
