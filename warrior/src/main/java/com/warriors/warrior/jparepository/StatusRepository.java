package com.warriors.warrior.jparepository;

import com.warriors.warrior.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRepository extends JpaRepository<Status,Integer> {
}
