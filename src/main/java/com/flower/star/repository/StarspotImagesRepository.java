package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.StarspotImages;

@Repository
public interface StarspotImagesRepository extends JpaRepository<StarspotImages, Integer> {

}
