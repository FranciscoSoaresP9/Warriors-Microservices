package com.warriors.status.jparepository;


import com.warriors.status.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class that do the CRUD operations
 */
public interface StatusRepository extends JpaRepository<Status,Integer> {
}
