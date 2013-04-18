/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.tela.generico;

import br.com.eswiv.dao.IDAOGenerico;
import com.zap.arca.JATable;

/**
 *
 * @author Edidelson
 */
public interface IFuncionalidades {
    
    public void exibirDados(IDAOGenerico dao, JATable table);
}
