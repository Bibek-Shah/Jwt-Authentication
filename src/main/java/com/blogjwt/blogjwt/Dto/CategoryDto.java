package com.blogjwt.blogjwt.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {


    private Long categoryId;
    @NotBlank
    @Size(min = 4, max = 50, message = "min 4 and max 50 characters")
    private String categoryName;
    private String categoryDescription;

}
