package br.com.eswiv.tela.tablemodel;

import br.com.eswiv.modelo.Usuario;
import java.util.List;

/**
 *
 * @author Everton
 */
public class UsuariosTableModel extends RowTableModel {

    public static final int[] DEFAULT_COL_SIZES = new int[] {
        50,     //Código
        150,    //Nome 
        80,     //Apelido 
        80,     //Inclusão 
        0,      //Inativo
    };
    
    {
        columns = new String[] {
            "Código", 
            "Nome", 
            "Apelido", 
            "Inclusão", 
            "Inativo"
        };
    }
    
    @Override
    public Usuario getRow(int index) {
        return (Usuario) cache.get(index);
    }

    @Override
    public List<Usuario> getData() {
        return cache;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario us = (Usuario) cache.get(rowIndex);
        switch(columnIndex) {
            case 0: return us.getCodigo();
            case 1: return us.getNome();
            case 2: return us.getApelido();
            case 3: return us.getDataInclusao();
            case 4: return us.isInativo();
            default: return null;
        }
    }

    @Override
    public Class getColumnClass(int col) {
        switch(col) {
            case 0: return Integer.class;
            case 1: return Object.class;
            case 2: return Object.class;
            case 3: return java.sql.Date.class;
            case 4: return Boolean.class;
            default: return null;
        }
    }
    
}
