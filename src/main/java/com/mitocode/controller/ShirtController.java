package com.mitocode.controller;

import com.mitocode.dto.ExamDTO;
import com.mitocode.dto.ShirtDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Exam;
import com.mitocode.model.Shirt;
import com.mitocode.service.IExamService;
import com.mitocode.service.IShirtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/shirt")
public class ShirtController {

    @Autowired
    private IShirtService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ShirtDTO>> findAll() {
        List<ShirtDTO> list = service.findAll().stream().map(p -> mapper.map(p, ShirtDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public Shirt save(@Valid @RequestBody ShirtDTO dto) {
        Shirt p = service.save(mapper.map(dto, Shirt.class));
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdShirt()).toUri();
        return p;
    }

 @GetMapping("/{id}")
    public ResponseEntity<ShirtDTO> findById(@PathVariable("id") Integer id) {
        ShirtDTO dtoResponse;
        Shirt obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, ShirtDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Shirt> update(@Valid @RequestBody ShirtDTO dto) {
        Shirt obj = service.findById(dto.getIdShirt());
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdShirt());
        }

        return new ResponseEntity<>(service.update(mapper.map(dto, Shirt.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        Shirt obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            service.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  /*
    @GetMapping("/hateoas/{id}")
    public EntityModel<ExamDTO> findByIdHateoas(@PathVariable("id") Integer id){
        ExamDTO dtoResponse;
        Exam obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, ExamDTO.class);
        }

        //localhost:8080/exams/{id}
        EntityModel<ExamDTO> resource = EntityModel.of(dtoResponse);
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());
        resource.add(link1.withRel("exam-info1"));
        resource.add(link2.withRel("exam-full"));

        return resource;
    }*/

}
