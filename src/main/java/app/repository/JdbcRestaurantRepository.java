package app.repository;

import app.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcRestaurantRepository implements RestaurantRepository {

    private static final RowMapper<Restaurant> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertRestaurant;

    @Autowired
    public JdbcRestaurantRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertRestaurant = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("restaurant")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(restaurant);

        if (restaurant.isNew()) {
            Number newKey = insertRestaurant.executeAndReturnKey(parameterSource);
            restaurant.setId(newKey.intValue());
            return restaurant;
        } else {
            if (namedParameterJdbcTemplate.update("UPDATE restaurant SET name=:name WHERE id=:id", parameterSource) == 0) {
                return null;
            }
        }
        return restaurant;
    }

    @Override
    public Restaurant get(int id) {
        List<Restaurant> result = jdbcTemplate.query("SELECT * FROM restaurant WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(result);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return jdbcTemplate.query("SELECT * FROM restaurant", ROW_MAPPER);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM restaurant WHERE id = ?", id) != 0;
    }
}
