package app.repository;

import app.model.Restaurant;

import java.util.Collection;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    Collection<Restaurant> getAll();

    boolean delete(int id);
}
