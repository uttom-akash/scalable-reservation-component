package org.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BlogDto {
    @NotBlank(message = "Title is required.")
    @Size(max = 100, message = "Title must not exceed 100 characters.")
    public String title;

    @NotBlank(message = "Content is required.")
    @Size(max = 5000, message = "Content must not exceed 5000 characters.")
    public String content;

    @NotBlank(message = "Author name is required.")
    @Size(max = 50, message = "Author name must not exceed 50 characters.")
    public String authorName;
}


