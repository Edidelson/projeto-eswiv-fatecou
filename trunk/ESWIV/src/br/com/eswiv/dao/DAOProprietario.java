/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.dao;

import br.com.eswiv.modelo.IModelo;
import br.com.eswiv.modelo.Proprietario;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class DAOProprietario extends DAOGenerico {

    @Override
    public Proprietario consultar(int codigo) {
        return consultar(codigo, false);
    }

    @Override
    public Proprietario consultar(int codigo, boolean comInativo) {
        return (Proprietario) super.consultar(Proprietario.class, codigo, false);
    }

    @Override
    public <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Proprietario> getLista() {
        return super.getLista("Proprietario.getAll");
    }
    
}
