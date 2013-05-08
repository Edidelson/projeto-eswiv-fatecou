/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.tela.tablemodel;

import br.com.eswiv.modelo.Categoria;
import java.util.List;

/**
 *
 * @author Edidelson
 */
public class CategoriaTableModel extends RowTableModel {

    {
        columns = new String[]{"Código", "Descrição"};
    }

    @Override
    public Categoria getRow(int index) {
        return (Categoria) cache.get(index);
    }

    @Override
    public List<Categoria> getData() {
        return cache;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria categoria = (Categoria) cache.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return categoria.getCodigo();
            case 1:
                return categoria.getDescricao();
            default:
                return "";
        }
    }

    @Override
    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                return null;
        }
    }
}
