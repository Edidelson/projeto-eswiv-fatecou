/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.dao;

import br.com.eswiv.modelo.Cidade;
import br.com.eswiv.modelo.IModelo;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Edidelson
 */
public class DAOCidade extends DAOGenerico {

    public static Cidade consultarPor(String restriction, String value) {
        abrirSessao();
        System.out.println(restriction + " >> " + value);
        List l = session.createCriteria(Cidade.class).add(Restrictions.eq(restriction,value)).list();
        if (l.size() > 0) {
            return (Cidade) l.get(0);
        }
        return null;
    }

    @Override
    public Cidade consultar(int codigo) {
        return consultar(codigo, false);
    }

    @Override
    public Cidade consultar(int codigo, boolean comInativo) {
        return (Cidade) super.consultar(Cidade.class,codigo, comInativo);
    }

    @Override
    public List<Cidade> getLista() {
        return super.getLista("Cidade.getAll");
    }

    @Override
    public <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
