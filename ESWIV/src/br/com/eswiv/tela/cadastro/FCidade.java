package br.com.eswiv.tela.cadastro;

import br.com.eswiv.dao.DAOCidade;
import br.com.eswiv.modelo.Cidade;
import br.com.eswiv.modelo.IModelo;
import br.com.eswiv.tela.generico.FrameGenerico;
import br.com.eswiv.tela.principal.DSobreSistema;
import br.com.eswiv.tela.tablemodel.CidadesTableModel;
import com.zap.arca.JANumberFormatField;
import com.zap.arca.LoggerEx;
import com.zap.arca.util.WindowUtils;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import table.model.ArcaTableModel;

/**
 *
 * @author Edidelson
 */
public class FCidade extends FrameGenerico {

    /**
     * Creates new form FCidade
     */
    private Cidade cidades;
    private NumberFormat nf;
    private NumberFormat nfIbge;
    private ArcaTableModel<CidadesTableModel> cidadeArcaTableModel;
    CidadesTableModel cidadesTableModel = new CidadesTableModel();

    public FCidade() {
        initComponents();
        setLocationRelativeTo(null);
        iniciar();
        actionMenu(INCLUSAO);
        util.setEnterButton(btOK);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        tbIncluir = new javax.swing.JToggleButton();
        tbAlterar = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btExcluir = new javax.swing.JToggleButton();
        btPesquisar = new javax.swing.JToggleButton();
        btFiltro = new javax.swing.JToggleButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCidades = new com.zap.arca.JATable();
        jScrollPane2 = new javax.swing.JScrollPane();
        plCampos = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        plCamposCidade = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        lbCodIBGE = new javax.swing.JLabel();
        tfCodIBGE = new javax.swing.JTextField();
        lnEstado = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();
        lbDDD = new javax.swing.JLabel();
        tfDDD = new javax.swing.JTextField();
        tfCodigo = new com.zap.arca.JATextField(6,0);
        btOK = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnSair = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnIncluir = new javax.swing.JMenuItem();
        mnAlterar = new javax.swing.JMenuItem();
        mnExcluir = new javax.swing.JMenuItem();
        mnLimpar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnAtualizar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnFiltrar = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        miAjudaConteudo = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cidades");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        buttonGroup1.add(tbIncluir);
        tbIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/add.png"))); // NOI18N
        tbIncluir.setToolTipText("Incluir");
        tbIncluir.setFocusable(false);
        tbIncluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbIncluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tbIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbIncluirActionPerformed(evt);
            }
        });
        jToolBar1.add(tbIncluir);

        buttonGroup1.add(tbAlterar);
        tbAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/edit.png"))); // NOI18N
        tbAlterar.setToolTipText("Alterar");
        tbAlterar.setFocusable(false);
        tbAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbAlterar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tbAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbAlterarActionPerformed(evt);
            }
        });
        jToolBar1.add(tbAlterar);
        jToolBar1.add(jSeparator1);

        buttonGroup1.add(btExcluir);
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/delete.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir");
        btExcluir.setFocusable(false);
        btExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        jToolBar1.add(btExcluir);

        buttonGroup1.add(btPesquisar);
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/pesquisar.png"))); // NOI18N
        btPesquisar.setToolTipText("Pesquisar");
        btPesquisar.setFocusable(false);
        btPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        jToolBar1.add(btPesquisar);

        buttonGroup1.add(btFiltro);
        btFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/filter.png"))); // NOI18N
        btFiltro.setToolTipText("Filtrar");
        btFiltro.setFocusable(false);
        btFiltro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btFiltro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFiltroActionPerformed(evt);
            }
        });
        jToolBar1.add(btFiltro);

        jSplitPane1.setDividerLocation(190);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setViewportView(tbCidades);
        tbCidades.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                alterar(tbCidades);
            }
        });

        jSplitPane1.setTopComponent(jScrollPane1);

        plCampos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        plCampos.setPreferredSize(new java.awt.Dimension(450, 200));

        plCamposCidade.setBackground(new java.awt.Color(255, 255, 255));
        plCamposCidade.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbCodigo.setText("Código:");

        lbNome.setText("Nome:");

        lbCodIBGE.setText("Cód. IBGE:");

        lnEstado.setText("Estado:");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        lbDDD.setText("DDD:");

        tfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodigoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout plCamposCidadeLayout = new javax.swing.GroupLayout(plCamposCidade);
        plCamposCidade.setLayout(plCamposCidadeLayout);
        plCamposCidadeLayout.setHorizontalGroup(
            plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plCamposCidadeLayout.createSequentialGroup()
                .addGroup(plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plCamposCidadeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(plCamposCidadeLayout.createSequentialGroup()
                                .addComponent(lbNome)
                                .addGap(10, 10, 10))
                            .addGroup(plCamposCidadeLayout.createSequentialGroup()
                                .addComponent(lbCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plCamposCidadeLayout.createSequentialGroup()
                                .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbCodIBGE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCodIBGE, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(plCamposCidadeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(plCamposCidadeLayout.createSequentialGroup()
                                .addComponent(lnEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(plCamposCidadeLayout.createSequentialGroup()
                                .addComponent(lbDDD)
                                .addGap(16, 16, 16)
                                .addComponent(tfDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        plCamposCidadeLayout.setVerticalGroup(
            plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plCamposCidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigo)
                    .addComponent(lbCodIBGE)
                    .addComponent(tfCodIBGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plCamposCidadeLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbNome))
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plCamposCidadeLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lnEstado))
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(plCamposCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plCamposCidadeLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbDDD))
                    .addComponent(tfDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tfCodigo.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if(tfCodigo.getText().equals("")) {
                    tbAlterar.setSelected(false);
                    tbIncluir.setSelected(true);
                } else {
                    tbAlterar.setSelected(true);
                    tbIncluir.setSelected(false);
                }
            }
            public void changedUpdate(DocumentEvent e) { }
            public void removeUpdate(DocumentEvent e) {
                if(tfCodigo.getText().equals("")) {
                    tbAlterar.setSelected(false);
                    tbIncluir.setSelected(true);
                } else {
                    tbAlterar.setSelected(true);
                    tbIncluir.setSelected(false);
                }
            }
        });

        jTabbedPane1.addTab("Cidade", plCamposCidade);

        btOK.setText("OK");
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.setAutoscrolls(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plCamposLayout = new javax.swing.GroupLayout(plCampos);
        plCampos.setLayout(plCamposLayout);
        plCamposLayout.setHorizontalGroup(
            plCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(plCamposLayout.createSequentialGroup()
                        .addComponent(btOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        plCamposLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btOK, jButton2});

        plCamposLayout.setVerticalGroup(
            plCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btOK))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(plCampos);

        jSplitPane1.setRightComponent(jScrollPane2);

        jMenu1.setText("Arquivo");

        mnSair.setText("Sair");
        mnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSairActionPerformed(evt);
            }
        });
        jMenu1.add(mnSair);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        mnIncluir.setText("Incluir");
        mnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnIncluirActionPerformed(evt);
            }
        });
        jMenu2.add(mnIncluir);

        mnAlterar.setText("Alterar");
        mnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAlterarActionPerformed(evt);
            }
        });
        jMenu2.add(mnAlterar);

        mnExcluir.setText("Excluir");
        mnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnExcluirActionPerformed(evt);
            }
        });
        jMenu2.add(mnExcluir);

        mnLimpar.setText("Limpar");
        mnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLimparActionPerformed(evt);
            }
        });
        jMenu2.add(mnLimpar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Exibir");

        mnAtualizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        mnAtualizar.setText("Atualizar");
        mnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAtualizarActionPerformed(evt);
            }
        });
        jMenu3.add(mnAtualizar);
        jMenu3.add(jSeparator2);

        mnFiltrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        mnFiltrar.setText("Filtrar...");
        mnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFiltrarActionPerformed(evt);
            }
        });
        jMenu3.add(mnFiltrar);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Ajuda");

        miAjudaConteudo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        miAjudaConteudo.setText("Conteúdo de Ajuda");
        jMenu4.add(miAjudaConteudo);
        jMenu4.add(jSeparator3);

        mnSobre.setText("Sobre...");
        mnSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSobreActionPerformed(evt);
            }
        });
        jMenu4.add(mnSobre);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbIncluirActionPerformed
        actionMenu(INCLUSAO);
    }//GEN-LAST:event_tbIncluirActionPerformed

    private void tbAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbAlterarActionPerformed
        actionMenu(ALTERACAO);
    }//GEN-LAST:event_tbAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        actionMenu(EXCLUSAO);
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        actionMenu(PESQUISAR);
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFiltroActionPerformed
        actionMenu(FILTRAR);
    }//GEN-LAST:event_btFiltroActionPerformed

    private void mnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_mnSairActionPerformed

    private void mnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnIncluirActionPerformed
        actionMenu(INCLUSAO);
    }//GEN-LAST:event_mnIncluirActionPerformed

    private void mnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAlterarActionPerformed
        actionMenu(ALTERACAO);
    }//GEN-LAST:event_mnAlterarActionPerformed

    private void mnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnExcluirActionPerformed
        actionMenu(EXCLUSAO);
    }//GEN-LAST:event_mnExcluirActionPerformed

    private void mnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLimparActionPerformed
        actionMenu(LIMPAR);
    }//GEN-LAST:event_mnLimparActionPerformed

    private void mnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAtualizarActionPerformed
        actionMenu(INCLUSAO);
    }//GEN-LAST:event_mnAtualizarActionPerformed

    private void mnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFiltrarActionPerformed
        actionMenu(FILTRAR);
    }//GEN-LAST:event_mnFiltrarActionPerformed

    private void mnSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSobreActionPerformed
        new DSobreSistema(this, true).setVisible(true);
    }//GEN-LAST:event_mnSobreActionPerformed

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        // Verifica se os campos foram preenchidos
        if (verificarCampos(camposVerificar)) {
            // Adiciona ou altera o objeto
            inserirOuAlterar();
            limparCampos();
        }
    }//GEN-LAST:event_btOKActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost
        if (tfCodigo.getText().equals("")) {
            return;
        }
        boolean encontrou = false;
        loop:
        for (int i = 0; i < cidadesTableModel.getRowCount(); i++) {
            int linha = Integer.valueOf(tfCodigo.getText());
            if (cidadesTableModel.getRow(i).getCodigo().equals(linha)) {
                tbCidades.changeSelection(i, 0, false, false);
                encontrou = true;
                break loop;
            }
        }
        if (!encontrou) {
            JOptionPane.showMessageDialog(null, "Código Inválido");
            limparCampos();
            tfNome.requestFocus();
        }
    }//GEN-LAST:event_tfCodigoFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FCidade().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btExcluir;
    private javax.swing.JToggleButton btFiltro;
    private javax.swing.JButton btOK;
    private javax.swing.JToggleButton btPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbEstado;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbCodIBGE;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDDD;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lnEstado;
    private javax.swing.JMenuItem miAjudaConteudo;
    private javax.swing.JMenuItem mnAlterar;
    private javax.swing.JMenuItem mnAtualizar;
    private javax.swing.JMenuItem mnExcluir;
    private javax.swing.JMenuItem mnFiltrar;
    private javax.swing.JMenuItem mnIncluir;
    private javax.swing.JMenuItem mnLimpar;
    private javax.swing.JMenuItem mnSair;
    private javax.swing.JMenuItem mnSobre;
    private javax.swing.JPanel plCampos;
    private javax.swing.JPanel plCamposCidade;
    private javax.swing.JToggleButton tbAlterar;
    private com.zap.arca.JATable tbCidades;
    private javax.swing.JToggleButton tbIncluir;
    private javax.swing.JTextField tfCodIBGE;
    private com.zap.arca.JATextField tfCodigo;
    private javax.swing.JTextField tfDDD;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables

    @Override
    public void iniciar() {
        dao = new DAOCidade();

        tbPrincipal = tbCidades;
        toggleButton = tbIncluir;
        ctChave = tfCodigo;

        tbCidades.setName("TB_FCIDADE");
        tbCidades.setModel(cidadesTableModel);

        camposVerificar = new Component[]{tfNome, tfDDD, tfCodIBGE, cbEstado};
        camposLimpar = new Component[]{tfCodIBGE, tfNome, tfDDD, tfCodigo};

        nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        nfIbge = NumberFormat.getInstance();
        nfIbge.setMaximumIntegerDigits(6);
        WindowUtils.nextEnter(plCamposCidade);
        WindowUtils.exitEsc(this);
        configurarSincronizacao(dao, tbCidades);
    }

    @Override
    public void limparCampos() {
        super.limparCampos();
        exibirDados(dao, tbCidades);
    }

    @Override
    public void inserirOuAlterar() {
        Cidade cidade = new Cidade();
        cidade.setNome(tfNome.getText());

        if (!tfCodIBGE.getText().equals("")) {
            cidade.setIbge(Integer.parseInt(tfCodIBGE.getText()));
        }
        cidade.setEstado((String) cbEstado.getSelectedItem());
        cidade.setDdd(tfDDD.getText());

        try {
            if (tbAlterar.isSelected()) {
                cidade.setCodigo(Integer.valueOf(tfCodigo.getText()));
                dao.alterar(cidade);
            } else {
                dao.adicionar(cidade);
            }
        } catch (RuntimeException ex) {
            LoggerEx.log(ex);
        }
    }

    @Override
    public void preencherCampos(IModelo m) {

        cidades = (Cidade) m;
        tfCodigo.setText(String.valueOf(cidades.getCodigo()));
        tfNome.setText(cidades.getNome());
        tfCodIBGE.setText(String.valueOf(cidades.getIbge()));
        tfDDD.setText(cidades.getDdd());
        cbEstado.setSelectedItem(cidades.getEstado());

    }

    /**
     * Desativa todos os componentes de um Array
     *
     * @param components - Component[] Array com os componentes a desativar
     */
    private void desativarCampos(Component[] components) {
        for (Component c : components) {
            c.setEnabled(false);
            if (c instanceof JANumberFormatField) {
                JANumberFormatField ftf = (JANumberFormatField) c;
                ftf.setText("");
            }
        }
    }

    /**
     * Ativa todos os campos de um Array de acordo com o produto selecionado
     *
     * @param components - Component[] Array com os componentes a ativar
     */
    private void ativarCampos(Component[] components) {
        for (Component c : components) {
            c.setEnabled(true);
        }
    }

    /**
     * Ativa os campos que foi passado no component
     */
    public void ativar() {
        Component[] ativarCampos = new Component[]{tfNome, tfDDD, tfCodIBGE, cbEstado,
            tbIncluir, tbAlterar, btExcluir, mnIncluir, mnAlterar, mnExcluir, tfCodigo, btOK};
        ativarCampos(ativarCampos);
    }

    @Override
    public void preencherTabela(int linha, IModelo i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
