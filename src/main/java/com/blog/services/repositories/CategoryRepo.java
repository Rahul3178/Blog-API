package com.blog.services.repositories;

import com.blog.services.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer>
{
    // here write additional methods /customs  methods.
}
