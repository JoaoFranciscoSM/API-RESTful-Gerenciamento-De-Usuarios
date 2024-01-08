package francisco.joao.gerenciamentodeclientes.dominio.usuarios;

public record UsuarioRecord(
        Long id,
        String email,
        String senha,
        TipoUsuario tipoUsuario
) {
}
