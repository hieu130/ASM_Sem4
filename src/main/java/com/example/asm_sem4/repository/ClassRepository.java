package com.example.asm_sem4.repository;

import com.example.asm_sem4.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer>
{
    List<ClassEntity> findAllByName(String name);
    List<ClassEntity> findAllByNameContainsIgnoreCase(String name);
    //List<ClassEntity> findAllByName(String name);
    List<ClassEntity> findAllByNameOrderByNameAsc(String name);
}
