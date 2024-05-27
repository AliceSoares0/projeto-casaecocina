package com.site.casaecocina.repositories;

import com.site.casaecocina.model.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<FoodModel, UUID> {}
