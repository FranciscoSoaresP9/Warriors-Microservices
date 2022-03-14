package com.warriors.points.services;

import com.warriors.points.jparepository.PointsRepository;
import com.warriors.points.model.Points;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PointService implements PointsService<Points> {

    private final PointsRepository pointsRepository;

    @Autowired
    public PointService(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }

    public Points get(int id) {
        return pointsRepository.findById(id).get();
    }



    public Points update(Points points){
        if(!checkMaxPoints(points)){
            return points;
        }
        Points pointsChanged = get(points.getId());
        pointsChanged.setDamage(points.getDamage());
        pointsChanged.setArmor(points.getArmor());
        pointsChanged.setLife(points.getLife());
        pointsChanged.setSpeed(points.getSpeed());
        pointsChanged.setPointsAvailable(points.getPointsAvailable());
        return pointsRepository.save(pointsChanged);
    }

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
