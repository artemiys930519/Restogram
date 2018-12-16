package app.repository;

import app.model.Restaurant;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static app.RestaurantTestData.*;

public class InMemoryRestaurantRepositoryTest {

    private static InMemoryRestaurantRepository repository;


    public void init() {
        repository = new InMemoryRestaurantRepository();
    }

    @Test
    void getAllTest() {
        assertMatch(repository.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);
    }

    @Test
    void deleteTest() {
        repository.delete(RESTAURANT1.getId());
        assertMatch(repository.getAll(), RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);
    }

    @Test
    void saveTest() {
        Restaurant result = getCreated();
        repository.save(result);
        assertMatch(repository.get(result.getId()), result);
    }

    @Test
    void updateTest() {
        repository.save(getUpdated());
        assertMatch(repository.get(getUpdated().getId()), getUpdated());
    }
}
