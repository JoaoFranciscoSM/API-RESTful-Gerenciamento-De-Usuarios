package francisco.joao.gerenciamentodeclientes.dominio.usuarios;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

    public Usuario(UsuarioDTO usuarioDTO) {
        this.id = usuarioDTO.id();
        this.email = usuarioDTO.email();
        this.senha = usuarioDTO.senha();
        this.tipoUsuario = usuarioDTO.tipoUsuario();
    }

}