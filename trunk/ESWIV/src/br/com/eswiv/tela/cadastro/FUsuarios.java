package br.com.eswiv.tela.cadastro;

import br.com.eswiv.dao.DAOUsuario;
import br.com.eswiv.modelo.IModelo;
import br.com.eswiv.modelo.Usuario;
import br.com.eswiv.tela.generico.FrameGenerico;
import br.com.eswiv.tela.principal.DSobreSistema;
import br.com.eswiv.tela.tablemodel.UsuariosTableModel;
import com.zap.arca.LoggerEx;
import com.zap.arca.auth.Criptografia;
import com.zap.arca.util.WindowUtils;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Date;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Edidelson, 31/12/2010
 */
public class FUsuarios extends FrameGenerico {

    private Criptografia crip;
    UsuariosTableModel usuariosTableModel = new UsuariosTableModel();
    private Usuario usuario;

    /**
     * Creates new form FModelo
     */
    public FUsuarios() {
        crip = new Criptografia();
        initComponents();
        tbUsuarios.setName("TB_USUARIOS");
        setLocationRelativeTo(null);
        iniciar();
        exibirDados(dao, tbUsuarios);
        actionMenu(INCLUSAO);
        util.setEnterButton(btOk);
    }

    public void iniciar() {
        dao = new DAOUsuario();

        tbPrincipal = tbUsuarios;
        toggleButton = tbIncluir;
        ctChave = tfCodigo;

        camposVerificar = new Component[]{tfNome, tfApelido};
        // Configurações da tabela
        camposLimpar = new Component[]{tfNome, tfCodigo, tfApelido, pfSenha, pfConfirmacao, checkBoxSupervisor, checkBoxCadastros,
            checkBoxLancamentos, checkBoxFechamentos, checkBoxRelatorios, checkBoxExtratos};
        tbUsuarios.setModel(usuariosTableModel);

        // Habilita mudança de campos com a tecla Enter
        WindowUtils.nextEnter(plCampos);

        // Configura a sincronização
        configurarSincronizacao(dao, tbUsuarios);
    }

    @Override
    public boolean verificarCampos(Component[] c) {
        if (super.verificarCampos(c)) {
            String senha = String.copyValueOf(pfSenha.getPassword());
            if (senha.length() < 3) {
                JOptionPane.showMessageDialog(null, "A senha deve possuir ao menos 4 caracteres");
                pfSenha.setText("");
                pfConfirmacao.setText("");
                pfSenha.requestFocus();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Verifica se a senha e confirmação conferem
     *
     * @return
     */
    private boolean verificarSenha() {
        String senha = String.copyValueOf(pfSenha.getPassword());
        String confirmacao = String.copyValueOf(pfConfirmacao.getPassword());
        if (!senha.equals(confirmacao)) {
            JOptionPane.showMessageDialog(null, "Senha e Confirmação não conferem");
            pfConfirmacao.setText("");
            pfSenha.setText("");
            pfSenha.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void inserirOuAlterar() {
        Usuario usuario = new Usuario();
        // Nome
        usuario.setNome(tfNome.getText());
        // Apeido
        usuario.setApelido(tfApelido.getText());
        // Senha
        usuario.setSenha(crip.criptografar(pfSenha.getPassword()));
        // Data de inclusão
        usuario.setDataInclusao(new Date(new java.util.Date().getTime()));
        // Está inativo?
        usuario.setInativo(cbInativo.isSelected());

        try {
            if (tbAlterar.isSelected()) {
                usuario.setCodigo(Integer.parseInt(tfCodigo.getText()));
                dao.alterar(usuario);
            } else {
                dao.adicionar(usuario);
            }
        } catch (RuntimeException ex) {
            LoggerEx.log(ex);
        }
    }

    @Override
    public void preencherCampos(IModelo m) {
        usuario = (Usuario) m;
        // Código
        tfCodigo.setText(usuario.getCodigo() + "");
        // Nome
        tfNome.setText(usuario.getNome());
        // Apelido
        tfApelido.setText(usuario.getApelido());
        // Está inativo?
        cbInativo.setSelected(usuario.isInativo());

    }

    // TODO Implementar permissões de usuário
    /**
     * Configura os demais check boxes caso 'Supervisor' esteja marcado
     *
     * @param supervisor
     */
    private void isSupervisor(boolean supervisor) {
        JCheckBox[] campos = new JCheckBox[]{checkBoxCadastros, checkBoxLancamentos,
            checkBoxFechamentos, checkBoxRelatorios, checkBoxExtratos};

        for (JCheckBox c : campos) {
            c.setSelected(supervisor);
            c.setEnabled(!supervisor);
        }
    }

    @Override
    public void limparCampos() {
        super.limparCampos();
        // Habilitando os campos de permissão
        JCheckBox[] campos = new JCheckBox[]{checkBoxCadastros, checkBoxLancamentos,
            checkBoxFechamentos, checkBoxRelatorios, checkBoxExtratos};
        for (JCheckBox c : campos) {
            c.setSelected(false);
            c.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plPermissoes = new javax.swing.JPanel();
        checkBoxSupervisor = new javax.swing.JCheckBox();
        checkBoxCadastros = new javax.swing.JCheckBox();
        checkBoxLancamentos = new javax.swing.JCheckBox();
        checkBoxFechamentos = new javax.swing.JCheckBox();
        checkBoxRelatorios = new javax.swing.JCheckBox();
        checkBoxExtratos = new javax.swing.JCheckBox();
        bgEditar = new javax.swing.ButtonGroup();
        plCampos = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new com.zap.arca.JATable(UsuariosTableModel.DEFAULT_COL_SIZES);
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        lbApelido = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        lbConfirmacao = new javax.swing.JLabel();
        tfCodigo = new com.zap.arca.JATextField(6,0);
        tfNome = new com.zap.arca.JATextField(40);
        tfApelido = new com.zap.arca.JATextField(10);
        pfSenha = new com.zap.arca.JAPasswordField();
        pfConfirmacao = new com.zap.arca.JAPasswordField();
        cbInativo = new javax.swing.JCheckBox();
        plBotoes = new javax.swing.JPanel();
        btOk = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        tbAtalhos = new javax.swing.JToolBar();
        tbIncluir = new javax.swing.JToggleButton();
        tbAlterar = new javax.swing.JToggleButton();
        btExcluir = new javax.swing.JButton();
        spBar = new javax.swing.JToolBar.Separator();
        btPesquisar = new javax.swing.JButton();
        btFiltro = new javax.swing.JButton();
        mbPrincipal = new javax.swing.JMenuBar();
        mnArquivo = new javax.swing.JMenu();
        miArquivoSair = new com.zap.arca.JAMenuItem();
        mnEditar = new javax.swing.JMenu();
        miEdiIncluir = new javax.swing.JMenuItem();
        miEdiAlterar = new javax.swing.JMenuItem();
        miEdiExcluir = new javax.swing.JMenuItem();
        miEditarLimpar = new com.zap.arca.JAMenuItem();
        mnExibir = new javax.swing.JMenu();
        miExibirAtualizar = new com.zap.arca.JAMenuItem();
        spExibir = new javax.swing.JPopupMenu.Separator();
        miExibirFiltro = new javax.swing.JMenuItem();
        mnAjuda = new javax.swing.JMenu();
        miAjudaSobre = new com.zap.arca.JAMenuItem();

        plPermissoes.setBackground(new java.awt.Color(255, 255, 255));
        plPermissoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Permissões"));

        checkBoxSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxSupervisor.setText("Supervisor");
        checkBoxSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxSupervisorActionPerformed(evt);
            }
        });

        checkBoxCadastros.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxCadastros.setText("Cadastros");

        checkBoxLancamentos.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxLancamentos.setText("Lançamentos");

        checkBoxFechamentos.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxFechamentos.setText("Fechamentos");

        checkBoxRelatorios.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxRelatorios.setText("Relatórios");

        checkBoxExtratos.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxExtratos.setText("Relatórios - Extratos");

        javax.swing.GroupLayout plPermissoesLayout = new javax.swing.GroupLayout(plPermissoes);
        plPermissoes.setLayout(plPermissoesLayout);
        plPermissoesLayout.setHorizontalGroup(
            plPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plPermissoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxSupervisor)
                    .addComponent(checkBoxCadastros))
                .addGap(18, 18, 18)
                .addGroup(plPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plPermissoesLayout.createSequentialGroup()
                        .addComponent(checkBoxLancamentos)
                        .addGap(18, 18, 18)
                        .addComponent(checkBoxRelatorios))
                    .addGroup(plPermissoesLayout.createSequentialGroup()
                        .addComponent(checkBoxFechamentos)
                        .addGap(18, 18, 18)
                        .addComponent(checkBoxExtratos)))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        plPermissoesLayout.setVerticalGroup(
            plPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plPermissoesLayout.createSequentialGroup()
                .addGroup(plPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxSupervisor)
                    .addComponent(checkBoxLancamentos)
                    .addComponent(checkBoxRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxCadastros)
                    .addComponent(checkBoxFechamentos)
                    .addComponent(checkBoxExtratos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Usuários");

        plCampos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jSplitPane1.setDividerLocation(140);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(500, 571));

        tbUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbUsuarios);
        tbUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                alterar(tbUsuarios);
            }
        });

        jSplitPane1.setTopComponent(jScrollPane1);

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 250));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbCodigo.setText("Código:");

        lbNome.setText("Nome:");

        lbApelido.setText("Apelido:");

        lbSenha.setText("Senha:");

        lbConfirmacao.setText("Confirmação:");

        tfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodigoFocusLost(evt);
            }
        });

        tfNome.setName("Nome"); // NOI18N

        tfApelido.setName("Apelido"); // NOI18N
        tfApelido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfApelidoFocusLost(evt);
            }
        });
        tfApelido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfApelidoKeyTyped(evt);
            }
        });

        pfSenha.setName("Senha"); // NOI18N
        pfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfSenhaKeyTyped(evt);
            }
        });

        pfConfirmacao.setName("Confirmação"); // NOI18N
        pfConfirmacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pfConfirmacaoFocusLost(evt);
            }
        });
        pfConfirmacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfConfirmacaoKeyTyped(evt);
            }
        });

        cbInativo.setBackground(new java.awt.Color(255, 255, 255));
        cbInativo.setText("Inativo");
        cbInativo.setFocusable(false);
        cbInativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInativoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbInativo)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbApelido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfApelido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbConfirmacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pfConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 128, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbApelido, lbCodigo, lbConfirmacao, lbNome, lbSenha});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbInativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbApelido)
                    .addComponent(tfApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenha)
                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbConfirmacao)
                    .addComponent(pfConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tfCodigo.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if(tfCodigo.getText().equals("")) {
                    tbAlterar.setSelected(false);
                    tfApelido.setEnabled(true);
                    tbIncluir.setSelected(true);
                } else {
                    tbAlterar.setSelected(true);
                    tfApelido.setEnabled(false);
                    tbIncluir.setSelected(false);
                }
            }
            public void changedUpdate(DocumentEvent e) { }
            public void removeUpdate(DocumentEvent e) {
                if(tfCodigo.getText().equals("")) {
                    tbAlterar.setSelected(false);
                    tfApelido.setEnabled(true);
                    tbIncluir.setSelected(true);
                } else {
                    tbAlterar.setSelected(true);
                    tfApelido.setEnabled(false);
                    tbIncluir.setSelected(false);
                }
            }
        });

        jTabbedPane1.addTab("Usuário", jPanel1);

        btOk.setText("OK");
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

        javax.swing.GroupLayout plBotoesLayout = new javax.swing.GroupLayout(plBotoes);
        plBotoes.setLayout(plBotoesLayout);
        plBotoesLayout.setHorizontalGroup(
            plBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCancelar)
                .addGap(1, 1, 1))
        );

        plBotoesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btOk});

        plBotoesLayout.setVerticalGroup(
            plBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btCancelar)
                .addComponent(btOk))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1)
                    .addComponent(plBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jSplitPane1.setRightComponent(jScrollPane2);

        javax.swing.GroupLayout plCamposLayout = new javax.swing.GroupLayout(plCampos);
        plCampos.setLayout(plCamposLayout);
        plCamposLayout.setHorizontalGroup(
            plCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plCamposLayout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        plCamposLayout.setVerticalGroup(
            plCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );

        tbAtalhos.setFloatable(false);
        tbAtalhos.setRollover(true);

        bgEditar.add(tbIncluir);
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
        tbAtalhos.add(tbIncluir);

        bgEditar.add(tbAlterar);
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
        tbAtalhos.add(tbAlterar);

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
        tbAtalhos.add(btExcluir);
        tbAtalhos.add(spBar);

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
        tbAtalhos.add(btPesquisar);

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
        tbAtalhos.add(btFiltro);

        mnArquivo.setText("Arquivo");

        miArquivoSair.setText("Sair");
        miArquivoSair.setPreferredSize(new java.awt.Dimension(120, 22));
        miArquivoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miArquivoSairActionPerformed(evt);
            }
        });
        mnArquivo.add(miArquivoSair);

        mbPrincipal.add(mnArquivo);

        mnEditar.setText("Editar");

        miEdiIncluir.setText("Incluir");
        miEdiIncluir.setPreferredSize(new java.awt.Dimension(120, 22));
        miEdiIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEdiIncluirActionPerformed(evt);
            }
        });
        mnEditar.add(miEdiIncluir);

        miEdiAlterar.setText("Alterar");
        miEdiAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEdiAlterarActionPerformed(evt);
            }
        });
        mnEditar.add(miEdiAlterar);

        miEdiExcluir.setText("Excluir");
        miEdiExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEdiExcluirActionPerformed(evt);
            }
        });
        mnEditar.add(miEdiExcluir);

        miEditarLimpar.setText("Limpar");
        miEditarLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEditarLimparActionPerformed(evt);
            }
        });
        mnEditar.add(miEditarLimpar);

        mbPrincipal.add(mnEditar);

        mnExibir.setText("Exibir");

        miExibirAtualizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        miExibirAtualizar.setText("Atualizar");
        miExibirAtualizar.setPreferredSize(new java.awt.Dimension(150, 22));
        miExibirAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExibirAtualizarActionPerformed(evt);
            }
        });
        mnExibir.add(miExibirAtualizar);
        mnExibir.add(spExibir);

        miExibirFiltro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        miExibirFiltro.setText("Filtro...");
        miExibirFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExibirFiltroActionPerformed(evt);
            }
        });
        mnExibir.add(miExibirFiltro);

        mbPrincipal.add(mnExibir);

        mnAjuda.setText("Ajuda");

        miAjudaSobre.setText("Sobre...");
        miAjudaSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAjudaSobreActionPerformed(evt);
            }
        });
        mnAjuda.add(miAjudaSobre);

        mbPrincipal.add(mnAjuda);

        setJMenuBar(mbPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tbAtalhos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbAtalhos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        // Verificando se os campos foram preenchidos
        if (verificarCampos(camposVerificar)) {
            // Verificando se a senha e confirmação estão iguais
            if (verificarSenha()) {
                // Verificando se está em modo de alteração
//                if(!tfCodigo.getText().equals("")) {
//                    // Autenticando o usuário
//                    int cod = Integer.parseInt(tfCodigo.getText());
//                    Usuario us = new DAOUsuario().consultar(cod);
//                    if(!DConfirmacaoUsuario.autenticar(this, us)) {
//                        return;
//                    }
//                }
                // Atualizando o usuário no banco de dados
                inserirOuAlterar();
                // Limpando os campos do formulário
                limparCampos();
                // Atualizando a tabela de usuários
                exibirDados(dao, tbUsuarios);
            }
        }
}//GEN-LAST:event_btOkActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void tfApelidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfApelidoFocusLost
        if (!tfApelido.getText().equals("")) {
            if (!new DAOUsuario().verificarApelido(tfApelido.getText())) {
                tfApelido.setText("");
                tfApelido.requestFocus();
                JOptionPane.showMessageDialog(null, "Apelido já utilizado por outro usuário");
            }
        }
}//GEN-LAST:event_tfApelidoFocusLost

    private void tfApelidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfApelidoKeyTyped
}//GEN-LAST:event_tfApelidoKeyTyped

    private void pfSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfSenhaKeyTyped
        if (pfSenha.getPassword().length == 10) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
        }
}//GEN-LAST:event_pfSenhaKeyTyped

    private void pfConfirmacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfConfirmacaoFocusLost
}//GEN-LAST:event_pfConfirmacaoFocusLost

    private void pfConfirmacaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfConfirmacaoKeyTyped
        if (pfSenha.getPassword().length == 10) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
        }
}//GEN-LAST:event_pfConfirmacaoKeyTyped

private void checkBoxSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxSupervisorActionPerformed
    isSupervisor(checkBoxSupervisor.isSelected());
}//GEN-LAST:event_checkBoxSupervisorActionPerformed

    private void tbIncluirActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_tbIncluirActionPerformed
        actionMenu(INCLUSAO);
     }//GEN-LAST:event_tbIncluirActionPerformed

    private void tbAlterarActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_tbAlterarActionPerformed
        actionMenu(ALTERACAO);
     }//GEN-LAST:event_tbAlterarActionPerformed

    private void btExcluirActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btExcluirActionPerformed
        actionMenu(EXCLUSAO);
     }//GEN-LAST:event_btExcluirActionPerformed

    private void btPesquisarActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btPesquisarActionPerformed
        actionMenu(PESQUISAR);
     }//GEN-LAST:event_btPesquisarActionPerformed

    private void btFiltroActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_btFiltroActionPerformed
        actionMenu(FILTRAR);
     }//GEN-LAST:event_btFiltroActionPerformed

    private void miArquivoSairActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_miArquivoSairActionPerformed

        this.dispose();
     }//GEN-LAST:event_miArquivoSairActionPerformed

    private void miEdiIncluirActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_miEdiIncluirActionPerformed
        actionMenu(INCLUSAO);
     }//GEN-LAST:event_miEdiIncluirActionPerformed

    private void miEdiAlterarActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_miEdiAlterarActionPerformed
        actionMenu(ALTERACAO);
     }//GEN-LAST:event_miEdiAlterarActionPerformed

    private void miEdiExcluirActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_miEdiExcluirActionPerformed
        actionMenu(EXCLUSAO);
     }//GEN-LAST:event_miEdiExcluirActionPerformed

    private void miExibirAtualizarActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_miExibirAtualizarActionPerformed
        actionMenu(INCLUSAO);
     }//GEN-LAST:event_miExibirAtualizarActionPerformed

    private void miExibirFiltroActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_miExibirFiltroActionPerformed
        actionMenu(FILTRAR);
     }//GEN-LAST:event_miExibirFiltroActionPerformed

    private void miEditarLimparActionPerformed( java.awt.event.ActionEvent evt ) {//GEN-FIRST:event_miEditarLimparActionPerformed
        actionMenu(LIMPAR);
    }//GEN-LAST:event_miEditarLimparActionPerformed

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost
        if (tfCodigo.getText().equals("")) {
            return;
        }
        boolean find = false;
        loop:
        for (int i = 0; i < usuariosTableModel.getRowCount(); i++) {
            int linha = Integer.valueOf(tfCodigo.getText());
            if (usuariosTableModel.getRow(i).getCodigo().equals(linha)) {
                tbUsuarios.changeSelection(i, 0, false, false);
                find = true;
                break loop;
            }
        }
        if (!find) {
            JOptionPane.showMessageDialog(null, "Código Inválido");
            limparCampos();
            tfNome.requestFocus();
        }
    }//GEN-LAST:event_tfCodigoFocusLost

    private void cbInativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbInativoActionPerformed

    private void miAjudaSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAjudaSobreActionPerformed
        new DSobreSistema(this, true).setVisible(true); 
    }//GEN-LAST:event_miAjudaSobreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FUsuarios().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgEditar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btFiltro;
    private javax.swing.JButton btOk;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JCheckBox cbInativo;
    private javax.swing.JCheckBox checkBoxCadastros;
    private javax.swing.JCheckBox checkBoxExtratos;
    private javax.swing.JCheckBox checkBoxFechamentos;
    private javax.swing.JCheckBox checkBoxLancamentos;
    private javax.swing.JCheckBox checkBoxRelatorios;
    private javax.swing.JCheckBox checkBoxSupervisor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbApelido;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbConfirmacao;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JMenuBar mbPrincipal;
    private com.zap.arca.JAMenuItem miAjudaSobre;
    private com.zap.arca.JAMenuItem miArquivoSair;
    private javax.swing.JMenuItem miEdiAlterar;
    private javax.swing.JMenuItem miEdiExcluir;
    private javax.swing.JMenuItem miEdiIncluir;
    private com.zap.arca.JAMenuItem miEditarLimpar;
    private com.zap.arca.JAMenuItem miExibirAtualizar;
    private javax.swing.JMenuItem miExibirFiltro;
    private javax.swing.JMenu mnAjuda;
    private javax.swing.JMenu mnArquivo;
    private javax.swing.JMenu mnEditar;
    private javax.swing.JMenu mnExibir;
    private javax.swing.JPasswordField pfConfirmacao;
    private javax.swing.JPasswordField pfSenha;
    private javax.swing.JPanel plBotoes;
    private javax.swing.JPanel plCampos;
    private javax.swing.JPanel plPermissoes;
    private javax.swing.JToolBar.Separator spBar;
    private javax.swing.JPopupMenu.Separator spExibir;
    private javax.swing.JToggleButton tbAlterar;
    private javax.swing.JToolBar tbAtalhos;
    private javax.swing.JToggleButton tbIncluir;
    private com.zap.arca.JATable tbUsuarios;
    private com.zap.arca.JATextField tfApelido;
    private com.zap.arca.JATextField tfCodigo;
    private com.zap.arca.JATextField tfNome;
    // End of variables declaration//GEN-END:variables

    @Override
    public void preencherTabela(int linha, IModelo i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
