package com.example.asm_sem4.service;


import com.example.asm_sem4.model.ClassEntity;
import com.example.asm_sem4.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassRepository classRepository;

    @Override
    public void saveClass(ClassEntity c) {
        classRepository.save(c);
    }

    @Override
    public void deleteClass(Integer id) {
        classRepository.deleteById(id);
    }

    @Override
    public ClassEntity findById(Integer id) {
        return classRepository.findById(id).get();
    }

    @Override
    public List<ClassEntity> findAll() {
        return classRepository.findAll();
    }

    @Override
    public List<ClassEntity> findAllByName(String name) {
        return classRepository.findAllByNameContainsIgnoreCase(name);
    }
}
