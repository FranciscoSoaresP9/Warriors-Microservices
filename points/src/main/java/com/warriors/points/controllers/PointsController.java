package com.warriors.points.controllers;

import com.warriors.points.jparepository.PointsRepository;
import com.warriors.points.model.Points;
import com.warriors.points.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/points")
public class PointsController {
    private final PointService pointService;
    private final PointsRepository pointsRepository;
    @Autowired
    public PointsController(PointService pointService, PointsRepository pointsRepository) {
        this.pointService = pointService;
        this.pointsRepository = pointsRepository;
    }

    @PostMapping("/savePoints")
    public Points savePoints( @RequestBody Points points) {

        return pointsRepository.save(points);
    }
    @PostMapping("/updatePoints")
    public Points updatePoints( @RequestBody Points points) {
        System.out.println(points);
        return pointService.update(points);
    }

}
