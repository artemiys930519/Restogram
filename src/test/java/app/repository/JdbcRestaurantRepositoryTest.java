package app.repository;


import app.model.Restaurant;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static app.RestaurantTestData.*;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcRestaurantRepositoryTest {

    @Autowired
    RestaurantRepository repository;

    @Rule
    public Stopwatch watch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            System.out.println(String.format("%-20s %d",description.getMethodName(),TimeUnit.NANOSECONDS.toMillis(nanos)));
        }
    };

    @Test
    public void getTest() {
        assertMatch(RESTAURANT1, repository.get(RESTAURANT1.getId()));
    }

    @Test
    public void deleteTest() {
        repository.delete(RESTAURANT1.getId());
        assertMatch(List.of(RESTAURANT2,RESTAURANT3,RESTAURANT4,RESTAURANT5),RESTAURANT2,RESTAURANT3,RESTAURANT4,RESTAURANT5);
    }

    @Test
    public void getAllTest() {
        assertMatch(repository.getAll(),restaurants);
    }

    @Test
    public void saveTest() {
        Restaurant result = repository.save(getCreated());
        assertMatch(repository.get(result.getId()),result);
    }
    @Test
    public void updateTest() {
        Restaurant result = repository.save(getUpdated());
        assertMatch(repository.get(result.getId()),result);
    }
}
