package com.zss.interview.springbootrestapi.repositories;

import com.zss.interview.springbootrestapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
