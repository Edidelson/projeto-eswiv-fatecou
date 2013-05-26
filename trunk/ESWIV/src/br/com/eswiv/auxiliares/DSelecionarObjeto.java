/*
 * Copyright (c) 1998-2011 Zap Informática, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Zap
 * Informática, Inc. ("Confidential Information"). You shall not disclose 
 * such Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Zap Informática.
 */
package br.com.eswiv.auxiliares;

import br.com.eswiv.auxiliares.genericos.DSelecionarGenerico;
import br.com.eswiv.dao.IDAOGenerico;
import br.com.eswiv.modelo.IModelo;
import br.com.eswiv.tela.tablemodel.RowTableModel;
import com.zap.arca.event.ISelecao;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.TableModel;

/**
 * <code>Dialog</code> genérico que pode ser usado nas consultas por objetos no
 * banco de dados ou em listas de um
 * <code>TableModel</code>. <p>
 *
 * Usando a
 * <code>Fluent Interface</code>, devem ser passados os parâmetros para chamada
 * do Dialog: <p>
 * <pre>
 *  new DSelecionarObjeto(getFrame(), true)
 *                   .name("TB_DEMPRESAS")
 *                   .title("Selecionar Empresa")
 *                   .model(new EmpresasTableModel())
 *                   .dao(DAOEmpresa.class)
 *                   .frame(FEmpresas.class).open();
 * </pre> Os parâmetros não são obrigatórios, mas sem alguns deles o
 * <code>Dialog</code> pode apresentar
 * <code>NullPointerException</code>, no caso de faltar o
 * <code>TableModel</code>. <p>
 *
 * $Revision:: 0 $: <br> $Date:: 2012-03-18 16:19:50#$: <br> $Author:: Renato $:
 * <br>
 *
 * @version 1.00, 18 Março 2012
 * @author Renato Justo
 */
public class DSelecionarObjeto extends DSelecionarGenerico {

    private TableModel kModel;
    private Class classFrame;
    private boolean isClose = true;

    public DSelecionarObjeto(Window parent, boolean modal, ISelecao iSelecao) {
        super(parent, modal, iSelecao);
        initComponents();
    }

    public DSelecionarObjeto(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public DSelecionarObjeto(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * DAO de consulta ao banco de dados.
     *
     * @param dao
     * @return
     */
    public DSelecionarObjeto dao(Class dao) {
        try {
            this.dao = (IDAOGenerico) dao.getClass().cast(dao).newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(DSelecionarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DSelecionarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    /**
     * Passa um coleção para ser exibida no Dialog.
     *
     * @param c
     * @return
     */
    public DSelecionarObjeto list(Collection c) {
        this.listaModelos = (List<IModelo>) c;
        return this;
    }

    /**
     * Título da janela do
     * <code>Dialog</code>.
     *
     * @param title
     * @return
     */
    public DSelecionarObjeto title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Frame a ser chamado pelo botão incluir, se NULL o botão ficará
     * desabilitado.
     *
     * @param frame
     * @return
     */
    public DSelecionarObjeto frame(Class frame) {
        this.classFrame = frame;
        return this;
    }

    /**
     * <code>TableModel</code> do Objeto a ser consultado.
     *
     * @param kModel
     * @return
     */
    public DSelecionarObjeto model(TableModel kModel) {
        this.kModel = kModel;
        if (table.getName() == null) {
            table.setName("TB_" + kModel.getClass().getSimpleName());
        }
        return this;
    }

    /**
     * Nome da
     * <code>JATable</code>, usado para carregar as preferências das colunas
     * selecionadas.
     *
     * @param name
     * @return
     */
    public DSelecionarObjeto name(String name) {
        table.setName(name);
        return this;
    }

    /**
     * Deve ser chamado obrigatoriamente para exibir o
     * <code>Dialog</code>, contém os métodos de inicialização.
     */
    public void open() {
        btIncluir.setEnabled((classFrame == null) ? false : true);
        iniciar();
        this.setVisible(true);
    }

    /**
     * Fecha o
     * <code>Dialog</code>.
     */
    public void close() {
        this.dispose();
    }

    @Override
    public void iniciar() {
        setLocationRelativeTo(null);

        // Configurando a tabela
        table.setModel(kModel);

        String[] columnNames = ((RowTableModel) table.getModel()).getColumnNames();

        cbCampos.setModel(new DefaultComboBoxModel(columnNames));
        cbCampos.setSelectedIndex(1);

        // Configurando os eventos para busca
        configurarFiltros(tfBuscar, cbCampos, table, btOk);

        if (dao != null) {
            exibirDados(dao, table);
        } else {
            exibirDados(listaModelos, table);
        }

        tfBuscar.requestFocus();
    }

    @Override
    public void btIncluir() {
        try {
            // frame.setAlwaysOnTop(true);
            ((JFrame) classFrame.newInstance()).setVisible(true);
        } catch (InstantiationException ex) {
            Logger.getLogger(DSelecionarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DSelecionarObjeto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selecionarObjeto(ISelecao frame, IModelo m) {
        frame.selecionarObjeto(m, m.getCodigo().toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnBusca = new javax.swing.JPanel();
        lbCampo = new javax.swing.JLabel();
        lbBuscar = new javax.swing.JLabel();
        tfBuscar = new javax.swing.JTextField();
        cbCampos = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new com.zap.arca.JATable();
        pnOpcoes = new javax.swing.JPanel();
        btOk = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btIncluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        lbCampo.setText("Campo:");

        lbBuscar.setText("Buscar por:");

        cbCampos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCampos.setFocusable(false);

        javax.swing.GroupLayout pnBuscaLayout = new javax.swing.GroupLayout(pnBusca);
        pnBusca.setLayout(pnBuscaLayout);
        pnBuscaLayout.setHorizontalGroup(
            pnBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBuscaLayout.createSequentialGroup()
                        .addComponent(lbCampo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCampos, 0, 696, Short.MAX_VALUE))
                    .addGroup(pnBuscaLayout.createSequentialGroup()
                        .addComponent(lbBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnBuscaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbBuscar, lbCampo});

        pnBuscaLayout.setVerticalGroup(
            pnBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCampo)
                    .addComponent(cbCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBuscar)
                    .addComponent(tfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        btOk.setText("Ok");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btIncluir.setText("Incluir...");
        btIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnOpcoesLayout = new javax.swing.GroupLayout(pnOpcoes);
        pnOpcoes.setLayout(pnOpcoesLayout);
        pnOpcoesLayout.setHorizontalGroup(
            pnOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnOpcoesLayout.createSequentialGroup()
                .addContainerGap(528, Short.MAX_VALUE)
                .addComponent(btOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btIncluir)
                .addContainerGap())
        );

        pnOpcoesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btIncluir, btOk});

        pnOpcoesLayout.setVerticalGroup(
            pnOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btIncluir)
                    .addComponent(btCancelar)
                    .addComponent(btOk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-791)/2, (screenSize.height-514)/2, 791, 514);
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        if(table.getSelectedRowCount() != 0) {
            IModelo m = getSelecao(table);
            selecionarObjeto(frame, m);
            this.dispose();
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirActionPerformed
        this.dispose();
        btIncluir();
    }//GEN-LAST:event_btIncluirActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        tfBuscar.requestFocus();
    }//GEN-LAST:event_formWindowGainedFocus
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btOk;
    private javax.swing.JComboBox cbCampos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBuscar;
    private javax.swing.JLabel lbCampo;
    private javax.swing.JPanel pnBusca;
    private javax.swing.JPanel pnOpcoes;
    private com.zap.arca.JATable table;
    private javax.swing.JTextField tfBuscar;
    // End of variables declaration//GEN-END:variables
}
