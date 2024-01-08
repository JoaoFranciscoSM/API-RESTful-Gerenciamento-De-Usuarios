package francisco.joao.gerenciamentodeclientes.repositorios;

import francisco.joao.gerenciamentodeclientes.dominio.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
}
