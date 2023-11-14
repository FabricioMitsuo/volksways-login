package com.senai.Volksways.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table( name= "tb_usuario")

public class UsuarioModel implements Serializable{

    @Serial
    private static final long serialVersionUid =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_usuario", nullable = false)
    private UUID id;

    private String nome;

    private String email;

    private String endereco;

    private String cep;

    private String url_img;


}
