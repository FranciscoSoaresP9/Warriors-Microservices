package com.warriors.points.controllers;

import com.warriors.points.model.Points;
import com.warriors.points.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PointsController {
    private final PointService pointService;

    @Autowired
    public PointsController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping("/updatePoints")
    public Points savePoints( @RequestBody Points points) {
       return pointService.update(points);
    }

}
