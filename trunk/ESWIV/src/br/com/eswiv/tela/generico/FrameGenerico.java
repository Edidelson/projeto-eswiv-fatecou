package br.com.eswiv.tela.generico;

import br.com.eswiv.auxiliares.DFiltro;
import br.com.eswiv.auxiliares.genericos.DFiltroGenerico;
import br.com.eswiv.dao.IDAOGenerico;
import br.com.eswiv.exceptions.CannotDeleteException;
import br.com.eswiv.modelo.IModelo;
import br.com.eswiv.tela.tablemodel.RowTableModel;
import br.com.util.Util;
import com.zap.arca.JADecimalFormatField;
import com.zap.arca.JANumberFormatField;
import com.zap.arca.JASelectPicker;
import com.zap.arca.JATable;
import com.zap.arca.JATextField;
import com.zap.arca.LoggerEx;
import com.zap.arca.event.DataEvent;
import com.zap.arca.event.DataListener;
import com.zap.arca.event.IDataController;
import com.zap.arca.util.DateUtils;
import com.zap.arca.util.StringUtils;
import com.zap.arca.util.WindowUtils;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.IconValues;
import org.jdesktop.swingx.renderer.StringValues;
import org.jdesktop.swingx.search.SearchFactory;
import table.model.ArcaTableModel;
import table.model.RowContainer;

/**
 *
 * 29/12/2010
 *
 * @author Everton
 */
public abstract class FrameGenerico extends javax.swing.JFrame implements IFuncionalidades {

    public static final byte INCLUSAO = 0;
    public static final byte ALTERACAO = 1;
    public static final byte EXCLUSAO = 2;
    public static final byte FILTRAR = 3;
    public static final byte PESQUISAR = 4;
    public static final byte LIMPAR = 5;
    public static byte OPERACAO;
    protected Util util = new Util();
    protected List<IModelo> listaModelos;
    protected JDialog dgFiltro;
    protected Component[] camposVerificar;
    protected Component[] camposLimpar;
    protected JToggleButton toggleButton;
    protected JATable tbPrincipal;
    protected Component ctChave;
    public IDAOGenerico dao;
    protected IDataController ctrl;
    protected DataListener sinc;
    private RowTableModel kModel;

    // Bloco de inicialização que aplica o ícone da aplicação
    {
        WindowUtils.setSystemLookAndFeel();
        WindowUtils.exitEsc(this);
        util.inserirIconeAplicacao(this);
        Thread.setDefaultUncaughtExceptionHandler(new LogExceptions());
    }

    /**
     * Creates new form FrameGenerico
     */
    public FrameGenerico() {
        initComponents();
    }

    /**
     * Método inicial que deve ser chamado depois da inicialização dos
     * componentes.
     */
    public abstract void iniciar();

    /**
     * Método que irá inserir ou alterar um objeto no banco de dados.
     */
    public abstract void inserirOuAlterar();

    /**
     * Preenche os campos do formulário com o objeto informado. É chamado no
     * método alterar.
     *
     * @param m uma classe que implemente IModelo. Deve-se fazer o casting para
     * o objeto desejado
     */
    public abstract void preencherCampos(IModelo m);

    /**
     * Preenche os campos da tabela com os dados de um objeto
     *
     * @param linha Int linha que será preenchida
     * @param i IModelo objeto que implemente IModelo que será usado para o
     * preenchimento da linha. Deve-se fazer o casting para o objeto desejado.
     */
    public abstract void preencherTabela(int linha, IModelo i);

    /**
     * Configura a sincronização que deve ocorrer quando Sessão de conexão é
     * fechada
     *
     * @param dao
     * @param tabela
     */
    protected void configurarSincronizacao(final IDAOGenerico dao, final JATable tabela) {
        sinc = new DataListener() {

            public void update(DataEvent de) {
                exibirDados(dao, tabela);
            }
        };
        ctrl = (IDataController) dao;
        ctrl.addDataListener(sinc);
    }

    /**
     * Antes de esconder o formulário remove o DataControler
     */
    @Override
    public void dispose() {
        if (ctrl != null && sinc != null) {
            ctrl.removeDataListener(sinc);
        }
        super.dispose();
    }

    /**
     * Verifica se o CPF ou CNPJ é válido
     *
     * @param ftf JFormattedTextField contendo o CPF/CPNJ
     * <p/>
     * @return TRUE se o documento for válido, FALSE caso contrário
     */
    public boolean validarCpfCnpj(JFormattedTextField ftf) {
        if (!Util.validarCNPJCPF(ftf.getText().replaceAll("([^0-9])+", ""))) {
            int res = JOptionPane.showConfirmDialog(null,
                    "CPF/CNPJ inválido, deseja continuar?",
                    "CNPJ/ CPF", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                return true;
            } else {
                ftf.requestFocus();
                return false;
            }
        }
        return true;
    }

    /**
     * Remove um objeto selecionado na JATable do banco de dados
     *
     * @param table JATable que será usada para pegar o objeto
     * @param dao IDAOGenericoold para acesso ao banco de dados
     * @param m IModelo que será excluído
     * @return TRUE se a exclusão foi realizada, FALSE caso contrário
     */
    public boolean removerRegistro(JATable table, IDAOGenerico dao) {

        IModelo modelo = null;
        int viewIndex = table.getSelectedRow();

        if ((viewIndex > -1) && viewIndex < table.getRowCount()) {

            if (table.getModel() instanceof ArcaTableModel) {
                modelo = ((ArcaTableModel<RowTableModel>) table.getModel()).getModel().get(table.convertRowIndexToModel(viewIndex));
            } else if (table.getModel() instanceof RowTableModel) {
                modelo = ((RowTableModel) table.getModel()).get(table.convertRowIndexToModel(viewIndex));
            }

            if (modelo == null) {
                int codigo = Integer.parseInt(table.getValueAt(viewIndex, "Código").toString());
                // Recuperando o objeto a ser excluído
                modelo = dao.consultar(codigo, true);
            }

            // Exibindo mensagem de confirmação
            int res = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro?", "Exclusão de registro", JOptionPane.YES_NO_OPTION);
            // Caso confirmada a exclusão
            if (res == JOptionPane.YES_OPTION) {
                try {
                    if (dgFiltro != null) {
                        ((DFiltro) dgFiltro).limpar();
                    }
                    // Exclui o objeto do banco de dados
                    dao.remover(modelo);
                    limparCampos();
                    return true;
                } catch (CannotDeleteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (RuntimeException ex) {
                    LoggerEx.log(ex);
                }
            }
        }
        return false;
    }

    /**
     * Verifica se os campos passados em um Array Estão preenchidos
     *
     * @param Array de componentes a verificar
     * @return true se todos os campos foram preenchidos
     */
    public boolean verificarCampos(Component[] components) {
         boolean estahPreenchido = true;
        // Itera sobre os componentes do array
        for (Component c : components) {
            // Verifica se é um JTextField
            if (c instanceof JATextField) {
                JATextField tf = (JATextField) c;
                if (tf.getText().trim().equals("")) {
                   estahPreenchido = false;
                }
            }
            // Verifica se é um JFormattedTextField
            if (c instanceof JFormattedTextField) {
                JFormattedTextField ftf = (JFormattedTextField) c;
                if ((ftf.getText().trim().equals("")) || (ftf.getText().equals("  .   .   /    -  "))
                        || (ftf.getText().equals("   .   .   -  ")) || (ftf.getText().equals("   "))
                        || (ftf.getText().equals("   .   .   ")) || (ftf.getText().equals("   -    "))) {
                   estahPreenchido = false;
                }
            }
            // Verifica se é um JANumberFormatField
            if (c instanceof JADecimalFormatField) {
                JADecimalFormatField ftf = (JADecimalFormatField) c;
                if (ftf.getValue().equals(BigDecimal.ZERO)) {
                    estahPreenchido = false;
                }
            }
            // Verifica se é um JANumberFormatField
            if (c instanceof JANumberFormatField) {
                JANumberFormatField ftf = (JANumberFormatField) c;
                if (ftf.getValue().equals(BigDecimal.ZERO)) {
                    estahPreenchido = false;
                }
            }
            // Verifica se é um JXDatePicker
            if (c instanceof JXDatePicker) {
                JXDatePicker dp = (JXDatePicker) c;
                if (dp.getDate() == null) {
                    estahPreenchido = false;
                }
            }
            if(!estahPreenchido){
                if(c.getName()!=null){
                    JOptionPane.showMessageDialog(null,"<html><body>Campo <b><font color=black>" +c.getName().toUpperCase()+ "</font color> </b> não preenchido.</body></html>");
                }else{
                    JOptionPane.showMessageDialog(null,"Campo obrigatório não preenchido.");
                }
                
                c.requestFocus();
                return false;
//                break;
            }
        }
        
        return true;
    }

    /**
     * Limpa os valores do componente de um JFrame. O primeiro componente do
     * array é o que receberá o foco.
     */
    public void limparCampos() {
        // Itera sobre os componentes verificando seus tipos e os limpando
        for (Component c : camposLimpar) {
            if (c.equals(ctChave) && OPERACAO == LIMPAR) {
                continue;
            }
            if (c instanceof JTextField) {
                JTextField tf = (JTextField) c;
                tf.setText("");
            } else if (c instanceof JADecimalFormatField) {
                JADecimalFormatField ftf = (JADecimalFormatField) c;
                ftf.setValue(BigDecimal.ZERO);
            } else if (c instanceof JANumberFormatField) {
                JANumberFormatField ftf = (JANumberFormatField) c;
                ftf.setValue(BigDecimal.ZERO);
            } else if (c instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) c;
                cb.setSelected(false);
            } else if (c instanceof JLabel) {
                JLabel lb = (JLabel) c;
                lb.setText(" ");
            } else if (c instanceof JXDatePicker) {
                JXDatePicker dp = (JXDatePicker) c;
                dp.setDate(null);
            } else if (c instanceof JComboBox) {
                JComboBox cb = (JComboBox) c;
                cb.setSelectedIndex(0);
            } else if (c instanceof JPasswordField) {
                JPasswordField jp = (JPasswordField) c;
                jp.setText("");
            } else if (c instanceof JASelectPicker) {
                JASelectPicker jxsp = (JASelectPicker) c;
                jxsp.setValue(null, "");
            } else if (c instanceof JTextArea) {
                JTextArea jta = (JTextArea) c;
                jta.setText("");
            }
        }
        camposLimpar[0].requestFocus();
    }

    /**
     * Carrega uma tabela a partir dos dados obtidos do método dao.getLista
     *
     * @param dao
     * @param table
     * @param model
     * @param tamanhos
     */
    public void exibirDados(IDAOGenerico dao, JATable table) {

//      RowTableModel model = (RowTableModel) table.getModel();
//      model.clear();
//      listaModelos = dao.getLista();        
//      
//      for(Object obj: listaModelos) {
//          model.add(obj);
//      }
//        
//      table.loadColumnIndex();
//      table.scrollRowToVisible(table.getRowCount() -1);

        if (table.getModel() instanceof ArcaTableModel) {
            kModel = ((ArcaTableModel<RowTableModel>) table.getModel()).getModel();
        } else {
            kModel = (RowTableModel) table.getModel();
        }

        kModel.clear();

        for (IModelo m : dao.getLista()) {
            kModel.add(m);
        }
    }

    /**
     * Carrega uma tabela a partir dos dados enviados na lista
     *
     * @param lista
     * @param table
     * @param model
     * @param tamanhos
     */
    public void exibirDados(Collection lista, JATable table) {
        RowTableModel model = (RowTableModel) table.getModel();
        model.clear();

        for (Object obj : lista) {
            model.add(obj);
        }
    }

    /**
     * Exibe os atributos de um Objeto em uma JATable
     *
     * @param dao classe DAO do objeto
     * @param table JATable que serão exibidos os atributos do objeto
     * @param campos String[] campos que serão exibidos na tabela
     * @param classes Class[] classe dos atributos que serão exibidos
     * @param tamanhos Int[] tamanho dos campos que serão exibidos
     */
    public void exibirDados(IDAOGenerico dao, final JATable table, final JTree treeGridFiltro) {

        if (table.getModel() instanceof ArcaTableModel) {
            kModel = ((ArcaTableModel<RowTableModel>) table.getModel()).getModel();
        } else {
            kModel = (RowTableModel) table.getModel();
        }

        kModel.clear();

        // Representa a linha da tabela
        int linha = 0;//listaModelos.size() - 1;
        // Itera sobre todas as linhas da tabela
        for (IModelo m : dao.getLista()) {
            // preenche a tabela
            preencherTabela(linha, m);
            // muda de linha
            linha++;
        }

        //kModel.updateModel();

    }

    /**
     * Retorna a lista de objetos que implementam IModelo
     *
     * @return
     */
    public List<IModelo> getListaModelos() {
        return listaModelos;
    }

    /**
     * Preenche os campos do formulário de acordo com objeto selecionado em uma
     * linha da JATable
     *
     * @param table JATable que contém os objetos
     */
    public void alterar(JATable table) {

        IModelo modelo = null;

        // Obtenho o indice
        int viewIndex = table.getSelectedRow();

        // Indice do modelo
        int modelIndex = 0;

        // Se for original
        if (viewIndex != -1) {
            modelIndex = table.convertRowIndexToModel(viewIndex);
        }

        // Se for ArcaTableModel
        if (table.getModel() instanceof ArcaTableModel) {
            if ((viewIndex > -1) && viewIndex < table.getRowCount()
                    && ((ArcaTableModel<RowTableModel>) table.getModel()).getRow(modelIndex).isOriginal()) {

                RowContainer rc = ((ArcaTableModel) table.getModel()).getRow(modelIndex);

                modelo = ((ArcaTableModel<RowTableModel>) table.getModel()).getModel().get(
                        rc.getOriginalRowNumber());
            }
        } else if (table.getModel() instanceof RowTableModel) {
            if ((viewIndex > -1) && viewIndex < table.getRowCount()) {
                modelo = ((RowTableModel) table.getModel()).get(
                        modelIndex);
            }
        } else {
            Object cod = table.getValueAt(viewIndex, "Código");
            if (cod != null) {
                int codigo = Integer.parseInt(table.getValueAt(viewIndex, "Código").toString());

                // Itera sobre a lista para procurar o objeto com o código selecionado
                for (IModelo m : listaModelos) {
                    if (m.getCodigo().equals(codigo)) {
                        // Quando encontra o objeto sai do laço
                        modelo = m;
                        break;
                    }
                }
            }
        }

        if (modelo != null) {
            preencherCampos(modelo);
        }
    }

    /**
     * Abre uma JDialog para filtrar registro da JATable principal
     *
     * @param table JATable que principal que será filtrada
     */
    public void abrirFiltro(JATable table) {
        // Verifica se um DFiltro já não foi criado
        if (dgFiltro == null) {
            // Cria um DFiltro se necessário
            if (table.getModel() instanceof ArcaTableModel) {
                dgFiltro = new DFiltro(this, true, table, ((ArcaTableModel<RowTableModel>) table.getModel()).getModel().getColumnNames());
            } else {
                dgFiltro = new DFiltro(this, true, table, ((RowTableModel) table.getModel()).getColumnNames());
            }
            //dgFiltro = new DFiltroPropriedadeRural(this, true, table, campos);
        }
        // Mostra o DFiltro
        dgFiltro.setVisible(true);
    }

    /**
     * Abre uma JDialog para filtrar registro da JATable principal
     *
     * @param campos String[] campos para escolha do filtro. Devem estar na
     * mesma ordem dos campos da JATable principal
     * @param table JATable que principal que será filtrada
     */
    public void abrirFiltro(String[] campos, JATable table) {
        // Verifica se um DFiltro já não foi criado
        if (dgFiltro == null) {
            // Cria um DFiltro se necessário
            dgFiltro = new DFiltro(this, true, table, campos);
            //dgFiltro = new DFiltroPropriedadeRural(this, true, table, campos);
        }
        // Mostra o DFiltro
        dgFiltro.setVisible(true);
    }

    /**
     * Abre uma JDialog para filtrar registro da JATable principal
     *
     * @param campos String[] campos para escolha do filtro. Devem estar na
     * mesma ordem dos campos da JATable principal
     * @param table JATable que principal que será filtrada
     */
    public void abrirFiltro(String[] campos, JATable table, boolean propriedadeRural) {
        // Verifica se um DFiltro já não foi criado
        if (dgFiltro == null) {
            // Cria um DFiltro se necessário
            dgFiltro = new DFiltro(this, true, table, campos);
            //dgFiltro = new DFiltroPropriedadeRural(this, true, table, campos);
        }
        // Mostra o DFiltro
        dgFiltro.setVisible(true);
    }

    /**
     * Permite a entrada de apenas números em um JTextField ou
     * JFormattedTextField. Permite apenas 5 caracteres. Deve ser chamado no
     * evento KeyTaped do componente.
     *
     * @param evt KeyEvent disparado pelo KeyTaped to objeto
     * @param tf JTextField ou JFormattedTextField para contagem de caracteres
     */
    protected void permitirApenasNumeros(KeyEvent evt, JTextField tf) {
        if (!Character.isDigit(evt.getKeyChar()) || (tf.getText().length() == 5)) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
        }
    }

    /**
     * Consulta um objeto que implemente IModelo do banco de dados a partir do
     * DAO informado
     *
     * @param text - JTextField - contendo o código do objeto
     * @param dao - DAO referente ao objeto
     * @param label - JLabel para escrever a descrição do objeto
     * @return IModelo - resultado da consulta, NULL se não houver resultado
     */
    public IModelo getModeloPorCodigo(JATextField text, IDAOGenerico dao, JLabel label) {
        IModelo modelo = null;
        if (!text.getText().equals("")) {
            try {
                modelo = dao.consultar(Integer.parseInt(text.getText()), false);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Falha ao realizar consulta");
                return null;
            }
            if (modelo == null) {
                JOptionPane.showMessageDialog(null, "Registro não encontrado");
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

    public IModelo getModeloPorCodigoTeste(Object o, IDAOGenerico dao, JLabel label) {
        IModelo modelo = null;
        if (o != null) {
            try {
                modelo = dao.consultar(Integer.parseInt(o.toString()), false);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Falha ao realizar consulta");
                return null;
            }
            if (modelo == null) {
                JOptionPane.showMessageDialog(null, "Registro não encontrado");
                label.setText("");
//                text.setText("");
//                text.requestFocus();
            } else {
                label.setText(modelo.toString());
            }
        } else {
            label.setText("");
        }
        return modelo;
    }

    /**
     * Consulta um objeto que implemente IModelo do banco de dados a partir do
     * DAO informado
     *
     * @param text - JTextField - contendo o código do objeto
     * @param dao - DAO referente ao objeto
     * @param label - JLabel para escrever a descrição do objeto
     * @return IModelo - resultado da consulta, NULL se não houver resultado
     */
    public IModelo getModeloPorCodigoTexto(JTextField text, IDAOGenerico dao, JLabel label) {
        IModelo modelo = null;
        if (!text.getText().equals("")) {
            try {
                modelo = dao.consultarCodigo(String.valueOf(text.getText()), false);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Falha ao realizar consulta");
                return null;
            }
            if (modelo == null) {
                JOptionPane.showMessageDialog(null, "Registro não encontrado");
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

    protected void addAtalhoFiltro(JPanel pl, final DFiltroGenerico filtro) {
        // Adiciona uma ação para o evento FILTRO
        pl.getActionMap().put("FILTRO", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                filtro.setVisible(true);
            }
        });
        // Mapeia a ação na tecla F9
        pl.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "FILTRO");
    }
    /**
     *
     */
    ArrayList<java.sql.Date> datas = new ArrayList<java.sql.Date>();

    public void carregarDadosNodes() {

        datas.removeAll(datas);

        for (IModelo m : dao.getLista()) {
//            Orcamento  m2 = (Orcamento) ((Orcamento) m).clone();
//            datas.add(new java.sql.Date(m2.getDtOrcamento().getTime()));
        }

        // Em ordem decrescente do inicio do mandato
        Collections.sort(datas, new Comparator<Date>() {

            public int compare(Date o1, Date o2) {
                if (o1.before(o2)) {
                    return -1;
                } else if (o1.equals(o2)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    public void carregarTreeView(JTree treeGridFiltro) {

        carregarDadosNodes();
        treeGridFiltro.setCellRenderer(new DefaultTreeRenderer(IconValues.NONE, StringValues.DATE_TO_STRING));
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Data da Safra");

        String anoAnterior = "";
        String mesAnterior = "";
        String diaAnterior = "";
        DefaultMutableTreeNode ano = null;
        DefaultMutableTreeNode mes = null;
        DefaultMutableTreeNode dia = null;

        for (java.sql.Date index : datas) {

            if (!DateUtils.dateFormatter(index, "yyyy").equals(anoAnterior)) {
                ano = new DefaultMutableTreeNode(DateUtils.dateFormat(index, 3));
                root.add(ano);
            }
            
            if (!(StringUtils.leftPad(DateUtils.dateFormatter(index, "MM"), 2, "0") + "/" + DateUtils.dateFormatter(index, "yyyy")).equals(mesAnterior)) {
                mes = new DefaultMutableTreeNode(StringUtils.leftPad(DateUtils.dateFormatter(index, "MM"), 2, "0") + "/" + DateUtils.dateFormatter(index, "yyyy"));
                ano.add(mes);
            }
            
            if (!(StringUtils.leftPad(DateUtils.dateFormatter(index, "dd"), 2, "0") + "/" + StringUtils.leftPad(DateUtils.dateFormatter(index, "MM"), 2, "0") + "/" + DateUtils.dateFormatter(index, "yyyy")).equals(diaAnterior)) {
                dia = new DefaultMutableTreeNode(StringUtils.leftPad(DateUtils.dateFormatter(index, "dd"), 2, "0") + "/" + StringUtils.leftPad(DateUtils.dateFormatter(index, "MM"), 2, "0") + "/" + DateUtils.dateFormatter(index, "yyyy"));
                mes.add(dia);
            }

            anoAnterior = DateUtils.dateFormatter(index, "yyyy");
            mesAnterior = StringUtils.leftPad(DateUtils.dateFormatter(index, "MM"), 2, "0") + "/" + DateUtils.dateFormatter(index, "yyyy");
            diaAnterior = StringUtils.leftPad(DateUtils.dateFormatter(index, "dd"), 2, "0") + "/" + StringUtils.leftPad(DateUtils.dateFormatter(index, "MM"), 2, "0") + "/" + DateUtils.dateFormatter(index, "yyyy");
        }

        treeGridFiltro.setModel(new javax.swing.tree.DefaultTreeModel(root));
    }

    public void filtrar(String data, JATable table) {
        if (data != null && data.length() != 0 && !data.equals("Data da Safra")) {
            if (data.length() == 4) {
                data = "01/01/" + data;
                Util.filtrarColuna(DateUtils.dateFormatter(DateUtils.date(data), "yyyy"), table, 4);
            } else if (data.length() == 7) {
                data = "01/" + data;
                Util.filtrarColuna(DateUtils.dateFormatter(DateUtils.date(data), "yyyy-MM"), table, 4);
            } else {
                Util.filtrarColuna(DateUtils.dateFormatter(DateUtils.date(data), "yyyy-MM-dd"), table, 4);
            }
        } else {
            Util.filtrarColuna("", table, 4);
        }
        table.getRowSorter().modelStructureChanged();
    }

    /**
     * Define a ação a ser realizada de acordo com a operação passada como
     * parâmetros.
     */
    public void actionMenu(byte operacao) {

        FrameGenerico.OPERACAO = operacao;

        switch (OPERACAO) {
            case INCLUSAO:
                exibirDados(dao, tbPrincipal);
                limparCampos();
                toggleButton.setSelected(true);
                break;
            case ALTERACAO:
                if (tbPrincipal.getSelectedRowCount() == 0) {
                    toggleButton.setSelected(true);
                } else {
                    alterar(tbPrincipal);
                }
                break;
            case EXCLUSAO:
                removerRegistro(tbPrincipal, dao);
                break;
            case FILTRAR:
                abrirFiltro(tbPrincipal);
                break;
            case PESQUISAR:
                SearchFactory.getInstance().showFindInput(tbPrincipal, tbPrincipal.getSearchable());
                break;
            case LIMPAR:
                limparCampos();
                OPERACAO = INCLUSAO;
                break;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
    public FrameGenerico getFrame() {
        return this;
    }

    class LogExceptions implements Thread.UncaughtExceptionHandler {

        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
            LoggerEx.log(e);
        }
    }

    public boolean notExistsItem(List<?> collection, Object comparable) {
        for (Object index : collection) {
            if (index.equals(comparable)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método para validar o dia conforma o mês que estiver no sistema
     *
     * @param dia
     * @param validaDia
     * @return
     */
    public boolean validarDia(JATextField dia, Integer validaDia) {
        GregorianCalendar calendar = new GregorianCalendar();
        int mes = calendar.get(GregorianCalendar.MONTH);
        int ano = calendar.get(GregorianCalendar.YEAR);
        boolean anoBissexto = new GregorianCalendar().isLeapYear(ano);

        if (mes == 0 && validaDia > 31) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (anoBissexto == true && mes == 1 && validaDia > 29) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (anoBissexto == false && mes == 1 && validaDia > 28) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 2 && validaDia > 31) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 3 && validaDia > 30) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 4 && validaDia > 31) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 5 && validaDia > 30) {
            JOptionPane.showMessageDialog(this, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 6 && validaDia > 31) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 7 && validaDia > 31) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 8 && validaDia > 30) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 9 && validaDia > 31) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 10 && validaDia > 30) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        if (mes == 11 && validaDia > 31) {
            JOptionPane.showMessageDialog(null, "Dia inválido");
            dia.setText("");
            dia.requestFocus();
            return false;
        }
        return true;
    }
}