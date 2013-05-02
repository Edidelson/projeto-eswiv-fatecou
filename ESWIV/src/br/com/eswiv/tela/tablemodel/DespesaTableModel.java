/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.tela.tablemodel;

import br.com.eswiv.modelo.Despesas;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class DespesaTableModel extends RowTableModel{
    
    {
        columns=new String[]{"Valor", "Data", "Descrição"};
    }

    @Override
    public Despesas getRow(int index) {
        return (Despesas) cache.get(index);
    }

    @Override
    public List<Despesas> getData() {
        return cache;
    }

    @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        Despesas despesas = (Despesas) cache.get(rowIndex);
        switch (columnIndex){
            case 0:
                return despesas.getValor();
            case 1:
                return despesas.getDataDespesa();
            case 2:
                return despesas.getDescricao();
            default:
                return "";
        }
    }

    @Override
     public Class getColumnClass(int col) {
        switch (col){
            case 0:
                return Float.class;
            case 1:
                return Date.class;
            case 2:
                return String.class;
            default:    
                return null;
    }
 }
    
}
