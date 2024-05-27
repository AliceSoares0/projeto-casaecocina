package com.site.casaecocina.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FoodRecordDTO(@NotBlank String title, @NotBlank String image, @NotNull Integer price) {}
