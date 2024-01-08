package francisco.joao.gerenciamentodeclientes.servicos;

import francisco.joao.gerenciamentodeclientes.dominio.usuarios.Usuario;
import francisco.joao.gerenciamentodeclientes.dominio.usuarios.UsuarioRecord;
import francisco.joao.gerenciamentodeclientes.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ServicoUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // Cria um novo usuário no banco com base nos parâmetros fornecidos e retorna o usuário recém-criado.
    public Usuario criarUsuario(UsuarioRecord usuarioRecord) {
        Usuario novoUsuario = new Usuario(usuarioRecord);
        if(ValidarUsuario.validarUsuario(usuarioRecord.email(), usuarioRecord.senha(), usuarioRecord.tipoUsuario())) {
            return this.repositorioUsuario.save(novoUsuario);
        } else {
            throw new RuntimeException("");
        }
    }

    // Retorna uma lista de usuários presentes no banco de dados.
    public List<Usuario> listarUsuarios() {
        return this.repositorioUsuario.findAll();
    }

    // Remove o usuário do banco com o ID fornecido e retorna uma lista atualizada de usuários.
    public List<Usuario> deletarUsuario(Long id) {
        if(pesquisarUsuario(id) != null){
            this.repositorioUsuario.deleteById(id);
            return listarUsuarios();
        } else {
            throw new RuntimeException("");
        }
    }

    // Edita os atributos do usuário com o ID fornecido e retorna o usuário atualizado.
    public UsuarioRecord editarUsuario(Long id, UsuarioRecord usuarioRecord) {
        Optional<Usuario> usuarioOptional = this.repositorioUsuario.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setEmail(usuarioRecord.email());
            usuarioExistente.setSenha(usuarioRecord.senha());
            usuarioExistente.setTipoUsuario(usuarioRecord.tipoUsuario());
            if(ValidarUsuario.validarUsuario(usuarioRecord.email(), usuarioRecord.senha(), usuarioRecord.tipoUsuario())) {
                this.repositorioUsuario.save(usuarioExistente);
                return pesquisarUsuario(id);
            } else {
                throw new RuntimeException("");
            }
        } else {
            throw new RuntimeException("");
        }
    }

    // Retorna os atributos do usuário com o ID fornecido.
    public UsuarioRecord pesquisarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = this.repositorioUsuario.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioEncontrado = usuarioOptional.get();
            UsuarioRecord usuarioRecord = new UsuarioRecord(id, usuarioEncontrado.getEmail(),usuarioEncontrado.getSenha(), usuarioEncontrado.getTipoUsuario());
            return usuarioRecord;
        } else {
            throw new RuntimeException("");
        }
    }

}
