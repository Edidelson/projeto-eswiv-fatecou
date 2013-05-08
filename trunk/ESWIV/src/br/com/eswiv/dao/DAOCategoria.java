/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.dao;

import br.com.eswiv.modelo.Categoria;
import br.com.eswiv.modelo.IModelo;
import java.util.List;

/**
 *
 * @author Edidelson
 */
public class DAOCategoria extends DAOGenerico{

    @Override
    public Categoria consultar(int codigo) {
        return consultar(codigo, false);
    }

    @Override
    public Categoria consultar(int codigo, boolean comInativo) {
        return (Categoria) super.consultar(Categoria.class, codigo, comInativo);
    }

    @Override
    public <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Categoria> getLista() {
        return super.getLista("Categoria.getAll");
    }
    
}
