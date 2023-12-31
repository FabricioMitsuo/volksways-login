package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.annotation.Generated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="tb_usuarios")

public class UsuarioModel  implements Serializable {
    @Serial
    private static final long serialVersionUid = 1L;
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name="id_usuario" , nullable = false)
    private UUID id;

    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String cep;

    private String tipo_usuario;
    private String url_img;

}

