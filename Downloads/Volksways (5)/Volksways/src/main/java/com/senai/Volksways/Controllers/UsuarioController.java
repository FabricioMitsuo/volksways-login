package com.senai.Volksways.Controllers;


import com.senai.Volksways.Dtos.UsuarioDto;
import com.senai.Volksways.models.UsuarioModel;
import com.senai.Volksways.repositories.UsuarioRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.senai.Volksways.services.FileUploadService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;


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

    public ResponseEntity<Objects>buscarUsuario(@PathVariable(value = "idUsuario") UUID id) {
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);
        if (usuarioBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");

        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());

    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public ResponseEntity<Object> criarUsuario(@ModelAttribute @Valid UsuarioDto usuariodto){
        if (usuarioRepository.findByEmail(usuariodto.email()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ja cadastrado");
        }
        UsuarioModel novoUsuario = new UsuarioModel();
        BeanUtils.copyProperties(usuariodto, novoUsuario);

        String urlImagem;

        try {
            urlImagem = fileUploadService.fazerupload(usuariodto.imagem());
        }catch (IOException e){
            throw new RuntimeException(e);

        }

        novoUsuario.setUrl_img(urlImagem);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(novoUsuario));

    }
    @PutMapping( consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editarUsuario(@PathVariable(value = "idUsuario") UUID id,
                                                @ModelAttribute @Valid UsuarioDto usuarioDto){
        Optional<UsuarioModel>usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario nao encontrado");
        }
        UsuarioModel usuarioBd = usuarioBuscado.get();
        BeanUtils.copyProperties(usuarioDto, usuarioBd);

        String urlImagem;

        try {
            urlImagem = fileUploadService.fazerupload(usuariodto.imagem());
        }catch (IOException e){
            throw new RuntimeException(e);

        }

        usuarioBd.setUrl_img(urlImagem);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioBd));
    }
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "idUsuario") UUID id) {
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        usuarioRepository.delete(usuarioBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("usuario deletado com sucesso");
    }
}









