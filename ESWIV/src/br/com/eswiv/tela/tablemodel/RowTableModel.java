/*
 * Copyright (c) 1998-2011 Zap Informática, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Zap
 * Informática, Inc. ("Confidential Information"). You shall not disclose 
 * such Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Zap Informática.
 */
package br.com.eswiv.tela.tablemodel;


import br.com.eswiv.modelo.IModelo;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import table.model.ILinkTableModel;

/**
 * $Revision:: 80              $: <br>
 * $Date:: 2011-09-19 08:31:16#$: <br>
 * $Author:: Renato            $: <br>
 * 
 * @version    1.00, 11  Novembro 2011
 * @author     Renato Justo
 * @since      V1  
 */
 public abstract class RowTableModel extends AbstractTableModel implements ILinkTableModel {
    
    protected ArrayList cache = new ArrayList();
    protected String[] columns;

    public abstract IModelo getRow(int index);
    public abstract List<?> getData();
    public abstract Object getValueAt(int rowIndex, int columnIndex);
    
    @Override
    public abstract Class getColumnClass(int col);       
    
    public RowTableModel() {
    }    
    
    public RowTableModel(List l) {
        cache.addAll(l);
    }

    public IModelo get(int index) {      
        if (cache.get(index) instanceof IModelo)
            return (IModelo) cache.get(index);
        else 
            return null;
    }

    public void add(Object obj) {
        cache.add(obj);
        fireTableDataChanged();
    }                    
    
    public void setColumnNames(String[] columns) {
        this.columns = columns;
    }

    public String[] getColumnNames() {
        return columns;
    }        
    
    public void clear() {
        int size = cache.size();
        if (size > 0) {
            cache.clear();
            fireTableRowsDeleted(0, size - 1);
            fireTableDataChanged();
        }
    }

    public void remove(int row) {
        cache.remove(row);
        fireTableRowsDeleted(row, row);
        fireTableDataChanged();
    }

    public List<?> getValuesFilter() {
        return cache;
    }

    @Override
    public int getRowCount() {
        return cache.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }     
    
    @Deprecated
    public Object[] toArrayObject(Object value) {

        Class clazz = value.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Object[] o = new Object[fields.length];

        int i = -1;

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                o[++i] = field.get(value);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(RowTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(RowTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return o;
    }
}
