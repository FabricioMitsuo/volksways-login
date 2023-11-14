package com.volksway.volksway.controllers;

//import com.volksway.Dtos.UsuarioDto;
//import com.volksway.models.UsuarioModel;
//import com.volksway.repositories.UsuarioRepository;
//import com.volksway.service.FileUploadService;
//import jakarta.validation.Valid;
import dtos.UsuarioDto;
import jakarta.validation.Valid;
import models.UsuarioModel;
//import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestBody;
import repositories.UsuarioRepository;

//import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value= "/usuarios", produces ={"application/json"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    //FileUploadService fileUploadService;


@GetMapping
    public ResponseEntity<List<UsuarioModel>> listaUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());

}

@GetMapping("/{idUsuario}")
public ResponseEntity<Objects>buscarUsuario(@PathVariable(value = "idUsuario")UUID id){
   Optional<UsuarioModel>usuarioBuscdo = UsuarioRepository.findById(id);
   if (usuarioBuscado.isEmpt()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");

    }
    return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscdo.get());

    @PostMapping(consumes ={MediaType.MULTIPART_FORM_DATA_VALUE})

    public ResponseEntity<Object> criarUsuario(@ModelAttribute @Valid UsuarioDto usuariodto){
      if (usuarioRepository.findByEmail(usuaridto.email()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
        }

        UsuarioModel novoUsuario = new UsuarioModel();
        BeanUtils.copyProperties(usuariodto, novoUsuario);



        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(novoUsuario));
    }
}




