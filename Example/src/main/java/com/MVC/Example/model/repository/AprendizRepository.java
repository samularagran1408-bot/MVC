package com.MVC.Example.model.repository;

import com.MVC.Example.model.entity.Aprendiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AprendizRepository extends JpaRepository<Aprendiz, Long> {
}
