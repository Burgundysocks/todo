package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private Integer id;
    private Long userId;
    private Integer categoryId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String dueDate;
    private String createdAt;
    private String updatedAt;

}
