package app;

import app.model.Restaurant;
import app.repository.jdbc.JdbcRestaurantRepository;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        try(GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml")) {
            JdbcRestaurantRepository jdbc = context.getBean(JdbcRestaurantRepository.class);
            Restaurant restaurant = jdbc.get(1001);
            System.out.println(restaurant.getName());
        }
    }
}
