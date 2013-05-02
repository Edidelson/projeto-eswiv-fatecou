/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.dao;

import br.com.eswiv.modelo.Calculo;
import br.com.eswiv.modelo.IModelo;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class DAOCalculo extends DAOGenerico{

    @Override
    public Calculo consultar(int codigo) {
        return consultar (codigo, false);
    }

    @Override
    public Calculo consultar(int codigo, boolean comInativo) {
        return (Calculo) super.consultar(Calculo.class, codigo, comInativo);
    }

    @Override
    public <T extends IModelo> T consultarCodigo(String codigo, boolean comInativo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Calculo> getLista() {
        return super.getLista("Calculo.getAll");
    }
  }    