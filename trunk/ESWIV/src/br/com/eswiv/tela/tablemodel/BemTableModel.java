/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.tela.tablemodel;

import br.com.eswiv.modelo.Bem;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class BemTableModel extends  RowTableModel{
    
    {
         columns=new String[]{"Código", "Proprietário", "Grupo de Bens", "Valor Venal", "Data de Aquisição", "Turno"};
    }

    @Override
    public Bem getRow(int index) {
        return (Bem) cache.get(index);
    }

    @Override
    public List<Bem> getData() {
      return cache;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bem bem = (Bem) cache.get(rowIndex);
        switch (columnIndex){
            case 0:
                return bem.getCodigo();
            case 1:
                return bem.getProprietario()!=null?bem.getProprietario().getNome():"";
            case 2:
                return bem.getGrupo();
            case 3:
                return bem.getValorVenal().doubleValue();
            case 4:
                return bem.getAquisicao();
            case 5:
                return bem.getTurno();
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
                return String.class;
            case 2:
                return String.class;
            case 3:     
                return Float.class;
            case 4: 
                return Date.class;
            case 5:
                return String.class;
            default:
                return null;
        }
    }
    
}
