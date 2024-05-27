package com.site.casaecocina.controller;

import com.site.casaecocina.dtos.FoodRecordDTO;
import com.site.casaecocina.model.FoodModel;
import com.site.casaecocina.repositories.FoodRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @PostMapping("/foods")
    public ResponseEntity<FoodModel> saveFood(@RequestBody @Valid FoodRecordDTO foodRecordDTO) {
        var foodModel = new FoodModel();
        BeanUtils.copyProperties(foodRecordDTO, foodModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodRepository.save(foodModel));
    }

    @GetMapping("/foods")
    public ResponseEntity<List<FoodModel>> getAllFoods() {
        return ResponseEntity.status(HttpStatus.OK).body(foodRepository.findAll());
    }

    @GetMapping("/foods/{id}")
    public ResponseEntity<Object> getOneFood(@PathVariable(value = "id") UUID id) {
        Optional<FoodModel> foodO = foodRepository.findById(id);
        if (foodO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not founded");
        }
        return ResponseEntity.status(HttpStatus.OK).body(foodO.get());
    }

    @PutMapping("/foods/{id}")
    public ResponseEntity<Object> updateFood(@PathVariable(value = "id") UUID id, @RequestBody @Valid FoodRecordDTO foodRecordDTO) {
        Optional<FoodModel> foodO = foodRepository.findById(id);
        if (foodO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not founded");
        }
        var foodModel = foodO.get();
        BeanUtils.copyProperties(foodRecordDTO, foodModel);
        return ResponseEntity.status(HttpStatus.OK).body(foodRepository.save(foodModel));
    }

    @DeleteMapping("/foods/{id}")
    public ResponseEntity<Object> deleteFood(@PathVariable(value = "id") UUID id) {
        Optional<FoodModel> foodO = foodRepository.findById(id);
        if (foodO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food not founded");
        }
        foodRepository.delete(foodO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Food deleted successfully");
    }
}
