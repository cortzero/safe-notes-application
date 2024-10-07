package com.cortzero.safenotes.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDTO {

    private Long id;
    private String name;

}
