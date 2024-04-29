package com.icolak.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LibraryDto {

    private int id;
    private List<BookDto> userBookList;

    public LibraryDto(int id) {
        this.id = id;
        this.userBookList = new ArrayList<>();
    }
}