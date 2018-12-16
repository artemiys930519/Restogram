package app.repository;


import app.TimingExtension;
import app.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static app.RestaurantTestData.*;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"})

@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ExtendWith({SpringExtension.class,TimingExtension.class})
@ActiveProfiles("jdbc")

public class JdbcRestaurantRepositoryTest {

    @Autowired
    RestaurantRepository repository;

    @Test
    public void getTest() {
        assertMatch(RESTAURANT1, repository.get(RESTAURANT1.getId()));
    }

    @Test
    public void deleteTest() {
        repository.delete(RESTAURANT1.getId());
        assertMatch(List.of(RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5), RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);
    }

    @Test
    public void getAllTest() {
        assertMatch(repository.getAll(), restaurants);
    }

    @Test
    public void saveTest() {
        Restaurant result = repository.save(getCreated());
        assertMatch(repository.get(result.getId()), result);
    }

    @Test
    public void updateTest() {
        Restaurant result = repository.save(getUpdated());
        assertMatch(repository.get(result.getId()), result);
    }
}
