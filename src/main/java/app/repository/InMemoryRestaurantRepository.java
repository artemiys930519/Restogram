package app.repository;


import app.model.Restaurant;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryRestaurantRepository implements RestaurantRepository {

    private Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(10);

    {
        save(new Restaurant("Sergeys"));
        save(new Restaurant("Суши-work"));
        save(new Restaurant("Биография"));
        save(new Restaurant("Сахар"));
        save(new Restaurant("Wonderland"));
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return repository.values();
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        Objects.requireNonNull(restaurant,"restaurant must not be null");
        if (restaurant.isNew()) {
            restaurant.setId(counter.incrementAndGet());
            repository.put(restaurant.getId(), restaurant);
            return restaurant;
        }
        return repository.computeIfPresent(restaurant.getId(), (id, oldMeal) -> restaurant);
    }
}
