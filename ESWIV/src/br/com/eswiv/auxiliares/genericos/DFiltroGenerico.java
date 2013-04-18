/*
 * DFiltroGenerico.java
 *
 * Created on 09/06/2011, 10:58:33
 */
package br.com.eswiv.auxiliares.genericos;

import br.com.eswiv.dao.IDAOGenerico;
import br.com.eswiv.modelo.IModelo;
import br.com.util.Util;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.jdesktop.swingx.JXDatePicker;
import com.zap.arca.*;
import com.zap.arca.util.WindowUtils;

/**
 *
 * @author Everton
 */
public abstract class DFiltroGenerico extends javax.swing.JDialog {

    protected Util util;
    protected Component[] camposLimpar;
    protected Map<String, Object> parametros;   
    
    {
        util = new Util();
        WindowUtils.setSystemLookAndFeel();
        util.inserirIconeAplicacao(this);        
    }
    
    /** Creates new form DFiltroGenerico */
    public DFiltroGenerico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        fecharComEsc();
    }
    
    protected void limparCampos(Component[] comps) {
        for(Component c: comps) {
            if(c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if(c instanceof JXDatePicker) {
                ((JXDatePicker) c).setDate(null);
            } else if(c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            } else if(c instanceof JLabel) {
                ((JLabel) c).setText("");
            } else if(c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            }
        }
    }
    
    /**
     * Consulta um objeto que implemente IModelo do banco de dados a partir do DAO informado
     * @param text - JTextField - contendo o código do objeto
     * @param dao - DAO referente ao objeto
     * @param label - JLabel para escrever a descrição do objeto
     * @return IModelo - resultado da consulta, NULL se não houver resultado
     */
    public IModelo getModeloPorCodigo(JTextField text, IDAOGenerico dao, JLabel label) {
        IModelo modelo = null;
        if(!text.getText().equals("")) {
            try {
                modelo = dao.consultar(Integer.parseInt(text.getText()));
            } catch(RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao realizar consulta");
            }
            if(modelo == null) {
                // Exibir uma mensagem na tela
                label.setText("");
                text.setText("");
                text.requestFocus();
            } else {
                label.setText(modelo.toString());
            }
        } else {
            label.setText("");
        }
        return modelo;
    }
    
    /**
     * Verifica se um valor inicial é menor que um valo final
     * @param dInicial JANumberFormatField
     * @param dFinal JANumberFormatField
     * @param scale Scale - número de casas decimais
     * @return True se o número inicial é menor que o final ou False caso contrário
     */
    protected boolean verificarValores(JANumberFormatField dInicial, JANumberFormatField dFinal, int scale) {
        BigDecimal bInicial = dInicial.getValue().setScale(scale, RoundingMode.HALF_UP);
        BigDecimal bFinal = dFinal.getValue().setScale(scale, RoundingMode.HALF_UP);
        if(bInicial.compareTo(bFinal) > 0 && bFinal.compareTo(new BigDecimal(0.000)) != 0) {
            JOptionPane.showMessageDialog(null, "O valor inicial não deve ser maior que o valor final");
            dInicial.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Verifica se o valor inicial dos campos é menor que o valor final
     * @param vInicial
     * @param vFinal
     * @return 
     */
    protected boolean verificarValores(JTextField vInicial, JTextField vFinal) {
        int v1 = vInicial.getText().equals("")? 0: Integer.parseInt(vInicial.getText());
        int v2 = vFinal.getText().equals("")? 0: Integer.parseInt(vFinal.getText());
        if(v1 > v2 && v1 != 0 && v2 != 0) {
            JOptionPane.showMessageDialog(null, "O valor inicial não deve ser maior que o valor final");
            vInicial.requestFocus();
            return false;
        }
        return true;
    }
    
    protected boolean verificarDatas(JXDatePicker dtInicial, JXDatePicker dtFinal) {
        if(dtInicial.getDate() == null || dtFinal.getDate() == null) {
            return true;
        }
        if(dtInicial.getDate().after(dtFinal.getDate())) {
            JOptionPane.showMessageDialog(null, "A data inicial não pode ser maior que a final");
            dtInicial.requestFocus();
            return false;
        }
        return true;
    }
    
    public Map getParametros() {
        return parametros;
    }
    
    public abstract void preencherParametros();
    
    /**
     * Fecha o Dialog quando a tecla Esc é apertada
     * @param c - Algum JComponent do Dialog
     */
    private void fecharComEsc() {
        // Recebe o Map de teclas/ações e adiciona e adiciona a tecla ESC a ação fechar
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "fechar");
        // Define o evento a ser chamdo quando a ação fechar for executada
        this.getRootPane().getActionMap().put("fechar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
