package br.com.eswiv.auxiliares.genericos;

import br.com.eswiv.dao.IDAOGenerico;
import br.com.eswiv.modelo.IModelo;
import br.com.eswiv.tela.tablemodel.RowTableModel;
import br.com.util.Util;
import com.zap.arca.JATable;
import com.zap.arca.event.ISelecao;
import com.zap.arca.util.WindowUtils;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import table.model.ArcaTableModel;
import table.model.filter.TableFilter;

/**
 * @author Everton, 02/01/2011
 */
public abstract class DSelecionarGenerico extends javax.swing.JDialog {

    protected Util util = new Util();
    protected ISelecao frame;    
    protected List<IModelo> listaModelos;
    protected IDAOGenerico dao;
    protected Collection<IModelo> colecaoModelos;
    private RowTableModel kModel;

    // Bloco de inicialização que aplica o ícone da aplicação
    {
        WindowUtils.setSystemLookAndFeel();
        WindowUtils.exitEsc(this);
        util.inserirIconeAplicacao(this);
    }

    /** Creates new form DGenericoSelecionar */
    public DSelecionarGenerico( java.awt.Window parent, boolean modal, ISelecao iSelecao ) {
        super(parent);
        setModal(modal);
        frame = iSelecao;
        initComponents();       
    }

    /** Creates new form DGenericoSelecionar */
    public DSelecionarGenerico( java.awt.Window parent, boolean modal ) {
        super(parent);
        setModal(modal);
        frame = (ISelecao) parent;
        initComponents();     
    }

    /** Creates new form DGenericoSelecionar */
    public DSelecionarGenerico( java.awt.Frame parent, boolean modal ) {
        super(parent, modal);
        frame = (ISelecao) parent;
        initComponents();        
    }

    /** Creates new form DGenericoSelecionar */
    public DSelecionarGenerico( java.awt.Dialog parent, boolean modal ) {
        super(parent, modal);
        frame = (ISelecao) parent;
        initComponents();        
    }

    public DSelecionarGenerico( java.awt.Frame parent, boolean modal, boolean ao ) {
        super(parent, modal);
        //frame = (ISelecao) parent;        
        initComponents();
    }

    public void setFrameDialog( java.awt.Dialog dialog ) {
        frame = (ISelecao) dialog;
    }

    public abstract void btIncluir();

    /**
     * Inicia as variáveis necessárias para os demais métodos
     */
    public abstract void iniciar();

    /**
     * Retorna o objeto selecionado
     * @return IModelo selecionado
     */
    public IModelo getSelecao( JATable tabela ) {
        if ( listaModelos.isEmpty() ) {
            return null;
        }
        if (tabela.getSelectedRowCount() > 0 && tabela.getModel() instanceof RowTableModel ) {
            IModelo modelo = null;
            int viewIndex = tabela.getSelectedRow();                
            modelo = ((RowTableModel) tabela.getModel()).get(tabela.convertRowIndexToModel(viewIndex));
            return modelo;
        }
        if ( tabela.getSelectedRowCount() > 0 ) {
            int codigo = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), "Código").toString());
            IModelo modelo = null;
            for (IModelo m : listaModelos) {
                if ( Integer.parseInt(m.getCodigo().toString()) == codigo ) {
                    modelo = m;
                    break;
                }
            }
            return modelo;
        } else {
            return null;
        }
    }

    /**
     * Exibe os atributos de um Objeto em uma JATable
     * @param dao classe DAO do objeto
     * @param table JATable que serão exibidos os atributos do objeto
     */
    public void exibirDados( Collection<?> lista, JATable table ) {
        // Preenche a lista de objetos
        colecaoModelos = (Collection<IModelo>) lista;

        ((RowTableModel) table.getModel()).clear();
        for (Object obj : lista) {
            ((RowTableModel) table.getModel()).add(obj);
        }
    }

    /**
     * Exibe os atributos de um Objeto em uma JATable
     * @param dao classe DAO do objeto
     * @param table JATable que serão exibidos os atributos do objeto
     */
    public void exibirDados( IDAOGenerico dao, JATable table ) {

        /*********************************************************************/
        if ( table.getModel() instanceof ArcaTableModel ) {
            kModel = ((ArcaTableModel<RowTableModel>) table.getModel()).getModel();
        } else {
            kModel = (RowTableModel) table.getModel();
        }

        kModel.clear();

        /*********************************************************************/
        // ((RowTableModel) table.getModel()).clear();
        if ( dao != null ) {
            // Preenche a lista de objetos
            List<IModelo> modelos = dao.getLista();

            listaModelos = new ArrayList<IModelo>();

            for (IModelo m : modelos) {
                if ( !m.isInativo() ) {
                    listaModelos.add(m);
                }
            }

            // Ordena a lista
            Collections.sort(listaModelos, new ComparatorToString());

            for (Object obj : listaModelos) {
                //((RowTableModel) table.getModel()).add(obj);
                kModel.add(obj);
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
    public IModelo getModeloPorCodigo( JTextField text, IDAOGenerico dao, JLabel label ) {
        IModelo modelo = null;
        if ( !text.getText().equals("") ) {
            try {
                modelo = dao.consultar(Integer.parseInt(text.getText()));
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao realizar consulta");
            }
            if ( modelo == null ) {
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
     * // Autor: Renato
     * @param keyChar um char a ser validado.
     * @return verdadeiro caso o char passado como parâmetro estiver contido no 
     * Pattern, falso caso contrário.
     */
    protected boolean accept( char keyChar ) {
        // Compila um pattern em que somente serão aceitos, caracteres visiveis, 
        // espaços e backspace.
        Pattern p = Pattern.compile("[\\x20-\\x7E\b]");
        // Valida se o caracter está contido no Pattern.
        Matcher m = p.matcher(String.valueOf(keyChar));
        return m.matches();
    }

    /**
     * Configura os eventos do TextField e Tabela de busca para realizarem consulta a partir do que é digitado
     * @param campo JTextField - Campo onde é inserido o texto para pesquisa na tabela
     * @param campos JComboBox - Campos disponíveis para pesquisa
     * @param tabela JATable - Tabela que mostra os resultados
     * @param botao JButton - Botão que termina a execução do Dialog enviando o objeto selecionado para o formulário que o chamou
     */
    protected void configurarFiltros( final JTextField campo, final JComboBox campos, final JATable tabela, final JButton botao ) {
        // Adicionando o evento no campo
        campo.addKeyListener(new KeyListener() {

            public void keyTyped( KeyEvent e ) {
            }

            public void keyPressed( KeyEvent e ) {
            }

            public void keyReleased( KeyEvent evt ) {
                // Autor: Renato
                // Se a tecla pressionada for ENTER e houver uma linha selecionada na tabela
                // Então selecione o objeto
                if ( tabela.getSelectedRowCount() > 0 && evt.getKeyCode() == KeyEvent.VK_ENTER ) {
                    botao.doClick();
                }

                // Se a tecla pressionada for VK_UP no campo de busca, 
                // coloca o foco na tabela e seleciona a linha anterior.                
                if ( evt.getKeyCode() == KeyEvent.VK_UP ) {
                    tabela.requestFocus();
                    tabela.changeSelection((tabela.getSelectedRow() > -1) ? tabela.getSelectedRow() - 1 : 0, 0, false, false);
                }
                // Se a tecla pressionada for VK_DOWN no campo de busca,
                // coloca o foco na tabela e seleciona a próxima linha. 
                if ( evt.getKeyCode() == KeyEvent.VK_DOWN ) {
                    tabela.requestFocus();
                    tabela.changeSelection(tabela.getSelectedRow() + 1, 0, false, false);
                }
                if ( campo.getText().trim().length() != 0 ) {
                    TableFilter.filter(campo, tabela, campos.getSelectedIndex());
                } else if ( evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_UP ) {
                    exibirDados(dao, tabela);
                }
            }
        });

        // Adicionando o evento do mouse
        tabela.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 ) {
                    botao.doClick();
                }
            }

            @Override
            public void mousePressed( MouseEvent e ) {
            }

            @Override
            public void mouseReleased( MouseEvent e ) {
            }

            @Override
            public void mouseEntered( MouseEvent e ) {
            }

            @Override
            public void mouseExited( MouseEvent e ) {
            }
        });

        // Adicionando o evento na tabela
        tabela.addKeyListener(new KeyListener() {

            public void keyTyped( KeyEvent e ) {
            }

            public void keyReleased( KeyEvent e ) {
            }

            public void keyPressed( KeyEvent evt ) {
                // Autor: Renato
                // Se o caracter digitado na tabela for aceito então...
                if ( accept(evt.getKeyChar()) ) {
                    // Se for diferente de 8 == Backspace então...
                    if ( evt.getKeyChar() != 8 ) {
                        // O campo buscar recebe o valor do texto mais a tecla digitada.
                        campo.setText(campo.getText() + evt.getKeyChar());
                    } else {
                        // Senão a tecla for igual a Backspace então o campo buscar 
                        // recebe o valor do texto menos o ultimo caracter.
                        campo.setText(campo.getText().substring(0, campo.getText().length() - 1));
                    }
                    // O campo buscar recebe o focus.
                    campo.requestFocus();
                }
                // Se digitada a tecla ENTER então seleciona o objeto no GRID.
                if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
                    //selecionaObjeto();
                    botao.doClick();
                }

                // Se a tecla digita for SHIFT + TAB, volta para o campo de busca.
                if ( evt.getKeyCode() == KeyEvent.VK_TAB && evt.getModifiers() == KeyEvent.SHIFT_MASK ) {
                    campo.requestFocus();
                    // Senão a tecla digita for somente TAB, avança para o botão OK.
                } else if ( evt.getKeyCode() == KeyEvent.VK_TAB ) {
                    botao.requestFocus();
                }
            }
        });
    }

    private class ComparatorToString implements Comparator<Object> {

        public int compare( Object o1, Object o2 ) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
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
        setTitle("Selecionar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
