package francisco.joao.gerenciamentodeclientes.servicos;

import francisco.joao.gerenciamentodeclientes.dominio.usuarios.TipoUsuario;

public class ValidarUsuario {

    public static boolean validarUsuario(String email, String senha, TipoUsuario tipoUsuario) {
        return validarEmail(email) && validarSenha(senha) && validarTipo(tipoUsuario);
    }

    // Valida se o e-mail fornecido possui o domínio "@gmail.com".
    private static boolean validarEmail(String email) {
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
    private static boolean validarSenha(String senha) {
        return senha.length() > 7;
    }

    // Valida se o tipo de usuário fornecido é válido.
    private static boolean validarTipo(TipoUsuario tipoUsuario) {
        String tipoString = String.valueOf(tipoUsuario);
        if(tipoString.equalsIgnoreCase("ADM") || tipoString.equalsIgnoreCase("COMUM")) {
            return true;
        }
        return false;
    }

}
