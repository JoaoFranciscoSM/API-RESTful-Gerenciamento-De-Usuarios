package francisco.joao.gerenciamentodeclientes.servicos;

import francisco.joao.gerenciamentodeclientes.dominio.usuarios.TipoUsuario;
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
        if(validarEmail(usuarioRecord.email()) && validarSenha(usuarioRecord.senha()) && validarTipo(usuarioRecord.tipoUsuario())) {
            return this.repositorioUsuario.save(novoUsuario);
        } else {
            throw new RuntimeException("Os dados não foram preenchidos corretamente!");
        }
    }

    // Valida se o e-mail fornecido possui o domínio "@gmail.com".
    private boolean validarEmail(String email) {
        String[] arrayEmail = email.split("");
        String emailVerificar = "";
        for (int i = 0; i < arrayEmail.length ; i++) {
            if(arrayEmail[i].equalsIgnoreCase("@")) {
                for (int j = i; j < arrayEmail.length; j++) {
                    emailVerificar += arrayEmail[j];
                }
                break;
            }
        }
        if(emailVerificar.equalsIgnoreCase("@gmail.com")) {
            return true;
        }
        return false;
    }

    // Valida se a senha fornecida tem mais de 7 caracteres.
    private boolean validarSenha(String senha) {
        if(senha.length() > 7) {
            return true;
        }
        return false;
    }

    // Valida se o tipo de usuário fornecido é válido.
    private boolean validarTipo(TipoUsuario tipoUsuario) {
        String tipoString = String.valueOf(tipoUsuario);
        if(tipoString.equalsIgnoreCase("ADM") || tipoString.equalsIgnoreCase("COMUM")) {
            return true;
        }
        return false;
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
            throw new RuntimeException("Usuário não encontrado para o ID: " + id);
        }
    }

    // Edita os atributos do usuário com o ID fornecido e retorna o usuário atualizado.
    public UsuarioRecord editarUsuario(Long id, UsuarioRecord usuarioRecord) {
        Optional<Usuario> usuarioOptional = this.repositorioUsuario.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setEmail(usuarioRecord.email());
            usuarioExistente.setSenha(usuarioRecord.senha());
            this.repositorioUsuario.save(usuarioExistente);
            return pesquisarUsuario(id);
        } else {
            throw new RuntimeException("Usuário não encontrado para o ID: " + id);
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
            throw new RuntimeException("Usuário não encontrado para o ID: " + id);
        }
    }

}