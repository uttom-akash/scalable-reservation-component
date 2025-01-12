package org.api.controllers.v1;

import jakarta.validation.Valid;
import org.application.contracts.BlogService;
import org.application.dtos.BlogDto;
import org.application.dtos.BlogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService _blogService;

    @Autowired
    public BlogController(BlogService blogService){
        _blogService = blogService;
    }

    @PostMapping
    public String create(@Valid @RequestBody BlogDto createDto){
        return _blogService.create(createDto);
    }

    @GetMapping
    public String get() {
        return _blogService.get();
    }

    @GetMapping("/{id}")
    public String get(@PathVariable String id) {
        return _blogService.get(id);
    }

    @PutMapping("/{id}")
    public BlogView update(@Valid @RequestBody BlogDto updateDto, @PathVariable String id){

        return _blogService.update(updateDto, id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        _blogService.delete(id);
    }
}

