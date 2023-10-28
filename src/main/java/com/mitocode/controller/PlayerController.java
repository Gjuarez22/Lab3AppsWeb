package com.mitocode.controller;

import com.mitocode.dto.PlayerDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Player;
import com.mitocode.service.IPlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private IPlayerService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll() {
        List<PlayerDTO> list = service.findAll().stream().map(p -> mapper.map(p, PlayerDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public Player save(@Valid @RequestBody PlayerDTO dto) {
        Player p = service.save(mapper.map(dto, Player.class));
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdPlayer()).toUri();
        return p;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> findById(@PathVariable("id") Integer id) {
        PlayerDTO dtoResponse;
        Player obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, PlayerDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Player> update(@Valid @RequestBody PlayerDTO dto) {
        Player obj = service.findById(dto.getIdName());
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdName());
        }

        return new ResponseEntity<>(service.update(mapper.map(dto, Player.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        Player obj = service.findById(id);
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
