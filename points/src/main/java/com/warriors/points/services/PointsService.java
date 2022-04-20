package com.warriors.points.services;

import com.warriors.points.model.Points;

/**
 * Class which is the bridge between the database and java
 */
public interface PointsService<T>{
    Points update(T toUpdate);
}
