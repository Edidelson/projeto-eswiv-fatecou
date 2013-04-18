package br.com.util;

import br.com.eswiv.modelo.Usuario;
import com.zap.arca.auth.Criptografia;

/**
 * DOCUMENTAÇÃO DA CLASSE
 * <br><br>
 * FINALIDADE:
 * <br>
 *
 * <br><br>
 * HISTÓRICO DE DESENVOLVIMENTO:
 * <br>
 * 14/03/2011 - @author Everton - Primeira versão
 *
 * <br><br>
 * LISTA DE CLASSES INTERNAS:
 * <br>
 *
 * <br><br>
 * ERROS CONHECIDOS:
 * <br>
 *
 * <br><br>
 */

public class Administrador {

    private String nome;    
    private String apelido;
    private String apelido2;
    private String senha;

    public Administrador() {
        nome = "Administrador";
        apelido = "ADMIN";
        apelido2 = "ZAP";
        senha = new Criptografia().criptografar("lunna".toCharArray());
    }

    public Usuario autenticar(String apelido, String senha) {
        if( (this.apelido.equals(apelido) || this.apelido2.equals(apelido)) && (this.senha.equals(senha)) ) {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setApelido(apelido);
            return usuario;
        } else {
            return null;
        }
    }
}