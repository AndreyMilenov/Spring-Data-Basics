package org._07_bookshopsystem_spring_demo.data.repositories;

import org._07_bookshopsystem_spring_demo.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
