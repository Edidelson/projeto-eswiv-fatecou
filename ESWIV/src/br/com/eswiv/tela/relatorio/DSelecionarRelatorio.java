package br.com.eswiv.tela.relatorio;

import br.com.eswiv.tela.generico.DialogGenerico;
import br.com.util.Util;
import br.com.util.XMLControle;
import br.com.util.XMLRelatorio;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Renato
 * @since 03/05/2011
 */
public class DSelecionarRelatorio extends DialogGenerico {
    
    private JFrame filtro;
    private String grupo;
    private Util util;
    private List<XMLRelatorio> relatorios = null;
    private XMLControle xml;
    private DefaultListModel model = new DefaultListModel();
    
    public DSelecionarRelatorio(String grupo, String frame) throws Exception {
        this(new JFrame(), true, grupo, (JFrame) Class.forName(frame).newInstance());
    }
    
    public DSelecionarRelatorio(java.awt.Frame parent, boolean modal, String grupo, JFrame filtro) {
        super(parent, modal);
        
        this.filtro = filtro;
        this.grupo = grupo;
        
        util = new Util();        
        util.inserirIconeAplicacao(this);
        
        xml = new XMLControle();        
        
        initComponents();
        setLocationRelativeTo(null);
        
        carregarListaRelatorios();
        
        listRelatorios.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                XMLRelatorio item = getItemSelecionado();
                cbRelatorioPadrao.setSelected(item!=null ? item.isPadrao() : false);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spListRelatorios = new javax.swing.JScrollPane();
        listRelatorios = new javax.swing.JList();
        cbRelatorioPadrao = new javax.swing.JCheckBox();
        btImprimir = new javax.swing.JButton();
        btVisualizar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btIncluir = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Abrir Relatório");
        setResizable(false);

        listRelatorios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        spListRelatorios.setViewportView(listRelatorios);

        cbRelatorioPadrao.setText("Relatório Padrão");
        cbRelatorioPadrao.setEnabled(false);

        btImprimir.setText("Imprimir");
        btImprimir.setEnabled(false);
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        btVisualizar.setText("Visualizar");
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btIncluir.setText("Incluir");
        btIncluir.setEnabled(false);
        btIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirActionPerformed(evt);
            }
        });

        btAlterar.setText("Alterar");
        btAlterar.setEnabled(false);
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.setEnabled(false);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spListRelatorios)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbRelatorioPadrao))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAlterar, btCancelar, btExcluir, btImprimir, btIncluir, btVisualizar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spListRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbRelatorioPadrao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelar)
                    .addComponent(btExcluir)
                    .addComponent(btAlterar)
                    .addComponent(btIncluir)
                    .addComponent(btVisualizar)
                    .addComponent(btImprimir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
       if(listRelatorios.getSelectedIndex()==-1){
           return;
       }
        checarRelatorioPadrao();
        this.dispose();
        filtro.setVisible(true);
        if(filtro instanceof Filtro) {
            ((Filtro) filtro).setRelatorio(getItemSelecionado());
        }
    }//GEN-LAST:event_btVisualizarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este relatório?") 
                == JOptionPane.YES_OPTION) {
            xml.excluirRelatorio(getItemSelecionado());
        }
        carregarListaRelatorios();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed

    }//GEN-LAST:event_btImprimirActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirActionPerformed
    }//GEN-LAST:event_btIncluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btVisualizar;
    private javax.swing.JCheckBox cbRelatorioPadrao;
    private javax.swing.JList listRelatorios;
    private javax.swing.JScrollPane spListRelatorios;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Método responsável por carregar relatorios dentro do JList
     */
    private void carregarListaRelatorios() {
        
        model.clear();
                
        relatorios = xml.getRelatorios(grupo);
        
        int cont = 0, item = -1;

        for (XMLRelatorio index : relatorios) {
            model.addElement(index.getNome());
            listRelatorios.setModel(model);
            if (index.isPadrao()) {
                item = cont;
            }
            cont++;
        }

        if (item > -1) {
            listRelatorios.setSelectedIndex(item);
            cbRelatorioPadrao.setSelected(true);
        }

    }
    
    public void checarRelatorioPadrao() {
       
        if (cbRelatorioPadrao.isSelected()) {

            XMLRelatorio item = getItemSelecionado();
                        
            for (XMLRelatorio index : relatorios) {
                index.setPadrao(false);
            }              
            
            item.setPadrao(true);
            
            xml.atualizarRelatorioPadrao(relatorios);
        }
    }
    
    public XMLRelatorio getItemSelecionado() {      
        try {
            return relatorios.get(listRelatorios.getSelectedIndex());
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }        
    }
}