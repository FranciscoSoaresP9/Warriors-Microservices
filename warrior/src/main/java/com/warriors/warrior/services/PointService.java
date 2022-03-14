package com.warriors.warrior.services;

import com.warriors.warrior.jparepository.PointsRepository;
import com.warriors.warrior.model.Points;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PointService implements Services<Points>{

    private final PointsRepository pointsRepository;

    @Autowired
    public PointService(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }

    @Override
    public Points get(int id) {
        return pointsRepository.findById(id).get();
    }

    @Override
    public Iterable<Points> getAll() {
        return pointsRepository.findAll();
    }

    public void update(Points points){
        if(!checkMaxPoints(points)){
            return;
        }
        Points pointsChanged = get(points.getId());
        pointsChanged.setDamage(points.getDamage());
        pointsChanged.setArmor(points.getArmor());
        pointsChanged.setLife(points.getLife());
        pointsChanged.setSpeed(points.getSpeed());
        pointsChanged.setPointsAvailable(points.getPointsAvailable());

        pointsRepository.save(pointsChanged);
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
