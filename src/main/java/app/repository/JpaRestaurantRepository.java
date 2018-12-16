package app.repository;

import app.model.Restaurant;

import java.util.Collection;


public class JpaRestaurantRepository implements RestaurantRepository {

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public Collection<Restaurant> getAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return true;
    }
}
