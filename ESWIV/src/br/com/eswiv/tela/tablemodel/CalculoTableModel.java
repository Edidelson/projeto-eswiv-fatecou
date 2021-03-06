/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.tela.tablemodel;

import br.com.eswiv.modelo.Calculo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class CalculoTableModel extends RowTableModel {

    {
        columns = new String[]{"Código", "Bem", "Grupo do Bem", "Valor Venal", "Aquisição",
            "Data do Cálculo", "Valor Depreciado"};
    }

    @Override
    public Calculo getRow(int index) {
        return (Calculo) cache.get(index);
    }

    @Override
    public List<Calculo> getData() {
        return cache;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Calculo calculo = (Calculo) cache.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return calculo.getCodigo();
            case 1:
                return calculo.getBem().getDescricao();
            case 2:
                return calculo.getBem().getGrupo().getDescricao();
            case 3:
                return calculo.getBem().getValorVenal().doubleValue();
            case 4:
                return calculo.getBem().getAquisicao();
            case 5:
                return calculo.getDataCalculo();
            case 6:
                return calculo.getValorCalculo().doubleValue();
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
                return Double.class;
            case 4:
                return Date.class;
            case 5:
                return Date.class;
            case 6:
                return Double.class;
            default:
                return null;
        }
    }
}