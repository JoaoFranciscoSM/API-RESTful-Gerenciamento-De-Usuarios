package francisco.joao.gerenciamentodeclientes.controladores;


import francisco.joao.gerenciamentodeclientes.dominio.usuarios.Usuario;
import francisco.joao.gerenciamentodeclientes.dominio.usuarios.UsuarioDTO;
import francisco.joao.gerenciamentodeclientes.servicos.ServicoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class ControleUsuario {

    @Autowired
    private ServicoUsuario servicoUsuario;

    // EndPoint para o sistema web realizar um CREATED, retorna o usuário criado e um HttpStatus (CREATED).
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = servicoUsuario.criarUsuario(usuarioDTO);
        return  new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    // EndPoint para o sistema web realizar um READ, retorna uma lista de usuários e um HttpStatus (OK).
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        var usuarios = this.servicoUsuario.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // EndPoint para o sistema web realizar um DELETE, retorna uma lista atualizada de usuários e um HttpStatus (OK).
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Usuario>> deletarUsuario(@PathVariable Long id) {
        var usuarios = this.servicoUsuario.deletarUsuario(id);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // EndPoint para o sistema web realizar um UPDATE, retorna o usuário editado e um HttpStatus (OK).
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        servicoUsuario.editarUsuario(id, usuarioDTO);
        Usuario usuario = new Usuario(usuarioDTO);
        usuario.setId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // EndPoint para o sistema web realizar um READ, retorna o usuário procurado e um HttpStatus (OK).
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> detalharUsuario(@PathVariable Long id) {
        UsuarioDTO usuarioPesquisado = servicoUsuario.pesquisarUsuario(id);
        Usuario usuario = new Usuario(usuarioPesquisado);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
