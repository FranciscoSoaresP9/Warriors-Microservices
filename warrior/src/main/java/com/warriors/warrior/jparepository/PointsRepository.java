package com.warriors.warrior.jparepository;


import com.warriors.warrior.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Points, Integer> {
}
