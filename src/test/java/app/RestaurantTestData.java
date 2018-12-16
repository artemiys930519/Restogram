package app;

import app.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static int startCount = 999;
    public final static Restaurant RESTAURANT1 = new Restaurant(startCount + 1, "Sergeys");
    public final static Restaurant RESTAURANT2 = new Restaurant(startCount + 2, "Суши-work");
    public final static Restaurant RESTAURANT3 = new Restaurant(startCount + 3, "Биография");
    public final static Restaurant RESTAURANT4 = new Restaurant(startCount + 4, "Сахар");
    public final static Restaurant RESTAURANT5 = new Restaurant(startCount + 5, "Wonderland");
    public final static List<Restaurant> restaurants = List.of(RESTAURANT1,RESTAURANT2,RESTAURANT3,RESTAURANT4,RESTAURANT5);

    public static Restaurant getCreated() {
        return new Restaurant("Алиби");
    }

    public static Restaurant getUpdated() {
        Restaurant result = new Restaurant(RESTAURANT1.getId(),RESTAURANT1.getName());
        result.setName("Обновленный ресторан");
        return result;
    }
    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

}
