package francisco.joao.gerenciamentodeclientes.dominio.usuarios;

public record UsuarioDTO(
        Long id,
        String email,
        String senha,
        TipoUsuario tipoUsuario
) {
}
