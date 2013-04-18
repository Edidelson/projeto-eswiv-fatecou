/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.eswiv.dao;

import br.com.eswiv.modelo.IModelo;
import br.com.eswiv.modelo.Usuario;
import br.com.util.Util;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * DOCUMENTAÇÃO DA CLASSE
 * <br><br>
 * FINALIDADE:
 * <br>
 * Manipular objetos Usuario armazenados no banco de dados
 * <br><br>
 * HISTÓRICO DE DESENVOLVIMENTO:
 * <br>
 * 10/01/2011 - @author Everton - Primeira versão
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

public class DAOUsuario extends DAOGenerico {

    private static Usuario usuarioAtual;
    
    public static Usuario getUsuarioAtual() {
        return usuarioAtual;
    }
    
    public static void setUsuarioAtual(Usuario usuario) {
        usuarioAtual = usuario;
    }
    
    public Usuario consultar(int codigo) {
        return (Usuario) super.consultar(Usuario.class, codigo);
    }

    public Usuario consultar(int codigo, boolean comInativo) {
        return (Usuario) super.consultar(Usuario.class, codigo, comInativo);
    }

    public List<Usuario> getLista() {
        return super.getLista("Usuario.getAll");
    }
    
    /**
     * Verifica se o apelido e senha informados conferem com os dados armazendos no banco
     * @param apelido - String
     * @param senha - String
     * @return - True se os dados estão corretos, False caso contrário
     * @throws RuntimeException
     */
    public Usuario autenticar(String apelido, String senha) throws RuntimeException {          
        try {            
            abrirSessao();
            getSession().beginTransaction();
            Query query = getSession().getNamedQuery("Usuario.autenticar").
                    setParameter("apelido", apelido).setParameter("senha", senha);
            List<Usuario> usuarios = query.list();
            getSession().getTransaction().commit();
            if(usuarios.size() == 1) {
                return usuarios.get(0);
            } else {
                return null;
            }
        } catch(HibernateException ex) {
            getSession().getTransaction().rollback();
            ex.printStackTrace();
            Util.logException(ex);
            throw new RuntimeException();
        }
    }

    /**
     * Verifica se o apelido informado não está sendo utilizando para outro usuário
     * @param apelido - String
     * @return - True se o apelido estivier disponível, False caso contrário
     * @throws RuntimeException
     */
    public boolean verificarApelido(String apelido) throws RuntimeException {        
        try {
            abrirSessao();
            getSession().beginTransaction();
            Query query = getSession().getNamedQuery("Usuario.verificarApelido").
                    setParameter("apelido", apelido);
            List<Usuario> usuarios = query.list();
            getSession().getTransaction().commit();
            if(usuarios.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch(HibernateException ex) {
            getSession().getTransaction().rollback();
            ex.printStackTrace();
            Util.logException(ex);
            throw new RuntimeException();
        }
    }

    @Override
    public <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
