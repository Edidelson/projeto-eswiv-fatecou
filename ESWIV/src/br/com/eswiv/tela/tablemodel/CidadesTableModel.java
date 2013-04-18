/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.tela.tablemodel;

import br.com.eswiv.modelo.Cidade;
import java.util.List;

/**
 *
 * @author Edidelson
 */
public class CidadesTableModel extends RowTableModel{

    {
        columns = new String[]{"Código", "Nome", "Estado", "Código IBGE"};
    }
    
    @Override
    public Cidade getRow(int index) {
        return (Cidade) cache.get(index);
    }

    @Override
    public List<Cidade> getData() {
        return cache;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cidade cidade = getRow(rowIndex);
        switch (columnIndex){
            case 0:
                return cidade.getCodigo();
            case 1:
                return cidade.getNome();
            case 2:
                return cidade.getEstado();
            case 3:
                return cidade.getIbge();
                default:
                    return "";
        }
    }

    @Override
    public Class getColumnClass(int col) {
        switch (col){
            case 0:
                return Integer.class;
            case 1:
                return Object.class;
            case 2:
                return Object.class;
            case 3:
                return Integer.class;
                default:
                    return null;
        }
    }
    
}
