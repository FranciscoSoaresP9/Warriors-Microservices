package com.warriors.points.jparepository;


import com.warriors.points.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Points, Integer> {
}
