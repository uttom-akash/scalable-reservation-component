package org.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class Blog {
    public String Id;

    public String title;

    public String content;

    public String authorName;

    public Instant createdAt;

    public Instant updatedAt;
}
