package com.example.asm_sem4.service;

import com.example.asm_sem4.model.ClassEntity;

import java.util.List;

public interface  ClassService {

    public void saveClass(ClassEntity c);
    public void deleteClass(Integer id);
    public ClassEntity findById(Integer id);
    public List<ClassEntity> findAll();

    public List<ClassEntity> findAllByName(String name);
}
