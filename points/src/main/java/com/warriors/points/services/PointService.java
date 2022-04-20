package com.warriors.points.services;

import com.warriors.points.jparepository.PointsRepository;
import com.warriors.points.model.Points;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class which is the bridge between the database and java
 */
@Service
public class PointService implements PointsService<Points> {

    private final PointsRepository pointsRepository;

    @Autowired
    public PointService(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }

    /**
     * Method to get Points from DataBase
     * @param id
     * @return Points
     */
    public Points get(int id) {
        return pointsRepository.findById(id).get();
    }

//TODO CHECK IF THE UPDATE WORK WELL
    /**
     * Method to Update Points on DataBase
     * @param points
     * @return Points Updated
     */
    public Points update(Points points){
        if(!checkMaxPoints(points)){
            return points;
        }
        return pointsRepository.save(points);
    }

    /**
     * Method to check if the max of points are reached
     * @param points
     * @return true if the max doesn´t reached and false if reached
     */
    private boolean checkMaxPoints(Points points){
        if(points.getDamage()>Points.MAX_POINTS){
            return false;
        }
        if(points.getLife()>Points.MAX_POINTS){
            return false;
        }
        if(points.getArmor()>Points.MAX_POINTS){
            return false;
        }
        if(points.getSpeed()>Points.MAX_POINTS){
            return false;
        }
        return true;
    }
}
