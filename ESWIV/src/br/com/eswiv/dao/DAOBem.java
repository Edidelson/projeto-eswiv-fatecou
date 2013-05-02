/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.dao;

import br.com.eswiv.modelo.Bem;
import br.com.eswiv.modelo.IModelo;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class DAOBem extends DAOGenerico{

    @Override
    public Bem consultar(int codigo) {
        return consultar(codigo, false);
    }

    @Override
    public Bem consultar(int codigo, boolean comInativo) {
        return (Bem) super.consultar(Bem.class, codigo, comInativo);
    }

    @Override
    public <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Bem> getLista() {
        return super.getLista("Bem.getAll");
    }    
}
