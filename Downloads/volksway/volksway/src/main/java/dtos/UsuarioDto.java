package dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record UsuarioDto(
        @NotBlank String nome,
        @NotBlank @Email(message = "O email deve estar no formato válido") String email,

        @NotBlank String telefone,

        @NotBlank String senha,

        String dataNascimento,

        String cidade,

        String tipo_usuario,

        MultipartFile imagem

){
}
