/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.tela.tablemodel;

import br.com.eswiv.modelo.Proprietario;
import br.com.util.Util;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class ProprietarioTableModel extends RowTableModel {

    {
        columns = new String[]{"Código", "Nome", "CPF", "Data do Cadastro"};
    }

    @Override
    public Proprietario getRow(int index) {
        return (Proprietario) cache.get(index);
    }

    @Override
    public List<Proprietario> getData() {
        return cache;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proprietario p = (Proprietario) cache.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getCodigo();
            case 1:
                return p.getNome();
            case 2:
                return p.getCpf() != null ? Util.formatarCpf(p.getCpf()) : "";
            case 3:
                return p.getData_cadastro();
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
            case 2:
                return String.class;
            case 3:
                return Date.class;
            default:
                return null;
        }
    }
}
