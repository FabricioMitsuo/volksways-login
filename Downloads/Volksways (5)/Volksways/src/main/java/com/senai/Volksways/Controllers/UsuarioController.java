package com.senai.Volksways.Controllers;


import com.senai.Volksways.Dtos.UsuarioDto;
import com.senai.Volksways.models.UsuarioModel;
import com.senai.Volksways.repositories.UsuarioRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senai.Volksways.service.FileUploadService;


import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/usuarios", produces = {"application/json"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    FileUploadService fileUploadService;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>>listaUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());

         }

    @GetMapping("/{idUsuario}")







