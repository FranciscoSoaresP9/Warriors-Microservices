package com.warriors.points.jparepository;


import com.warriors.points.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class that uses a CRUD operations to iterate with DataBase
 */
public interface PointsRepository extends JpaRepository<Points, Integer> {
}
