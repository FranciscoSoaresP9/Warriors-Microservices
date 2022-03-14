package com.warriors.points.services;

import com.warriors.points.model.Points;

public interface PointsService<T>{
    Points update(T toUpdate);
}
