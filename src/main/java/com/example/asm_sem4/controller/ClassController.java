package com.example.asm_sem4.controller;

import com.example.asm_sem4.model.ClassEntity;
import com.example.asm_sem4.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<ClassEntity>> findAllClass() {
        List<ClassEntity> lsClass = classService.findAll();
        if(lsClass.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ClassEntity>>(lsClass, HttpStatus.OK);
    }


    //userbyname?name=oanh
    @RequestMapping(value = "classbyname", method = RequestMethod.GET)
    public ResponseEntity<List<ClassEntity>> findAllClass(@PathParam("name") String name) {
        List<ClassEntity> lsClass = classService.findAllByName(name);
        if(lsClass.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ClassEntity>>(lsClass, HttpStatus.OK);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<ClassEntity> saveNewClass(@RequestBody ClassEntity u) {
        classService.saveClass(u);
        return new ResponseEntity<ClassEntity>(u, HttpStatus.OK);
    }

    //http://localhost:8080/updateUser?id=1
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<ClassEntity> updateClass(
            @PathParam("id") Integer id,
            @RequestBody ClassEntity c) {
        ClassEntity oldClass = classService.findById(id);
        oldClass.setName(c.getName());
        oldClass.setRoom(c.getRoom());
        oldClass.setNote(c.getNote());
        classService.saveClass(oldClass);
        return new ResponseEntity<ClassEntity>(oldClass, HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClassEntity> updateClass2(
            @PathVariable(value = "id") Integer id,
            @RequestBody ClassEntity c) {
        ClassEntity oldClass = classService.findById(id);
        oldClass.setRoom(c.getRoom());
        oldClass.setName(c.getName());
        oldClass.setNote(c.getNote());
        classService.saveClass(oldClass);
        return new ResponseEntity<ClassEntity>(oldClass, HttpStatus.OK);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName()  + " "
                    + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        }

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ClassEntity> deleteClass(@PathVariable(value = "id") Integer id) {
        classService.deleteClass(id);
        return ResponseEntity.ok().build();
    }
}
