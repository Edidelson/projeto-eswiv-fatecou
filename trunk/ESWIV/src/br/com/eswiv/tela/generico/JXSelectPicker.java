package br.com.eswiv.tela.generico;

import br.com.eswiv.auxiliares.DSelecionarObjeto;
import br.com.eswiv.auxiliares.genericos.DSelecionarGenerico;
import br.com.eswiv.dao.IDAOGenerico;
import br.com.eswiv.modelo.IModelo;
import com.zap.arca.JASelectPicker;
import com.zap.arca.JATextField;
import com.zap.arca.event.ISelecao;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Renato
 */
public class JXSelectPicker extends JASelectPicker implements ISelecao {

    IModelo modelo;
    DSelecionarGenerico d;
    JLabel label;
    IDAOGenerico dao;
    Class objectIdClass = Integer.class;

    public JXSelectPicker() {
        addActionListener();
        addFocusListener();        
    }   
    
    
    public IModelo getObjeto() {
        return modelo;
    }

    public void setObjeto( IModelo modelo ) {
        Object old = getEditor().getValue();
        this.modelo = modelo;
        firePropertyChange("value", old, modelo);
        this.getEditor().setValue(modelo == null ? "" : modelo.getCodigo());
        if ( label != null ) {
            this.label.setText(modelo == null ? "" : modelo.toString());
        }
    }

    public void setLabel( JLabel label ) {
        this.label = label;
    }

    public void clear() {
        modelo = null;
        if ( label != null ) {
            label.setText("");
        }
        ((JATextField) getComponent(1)).setText("");
    }

    public void active( DSelecionarGenerico d, IDAOGenerico dao, JLabel label ) {
        this.d = d;
        this.dao = (IDAOGenerico) dao;
        this.label = label;
    }

    public void active( DSelecionarGenerico d, Class dao, JLabel label ) {
        try {
            this.d = d;
            this.dao = (IDAOGenerico) dao.newInstance();
            this.label = label;
        } catch (InstantiationException ex) {
            Logger.getLogger(JXSelectPicker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JXSelectPicker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void idType(Class clazz) {
        this.objectIdClass = clazz;
    }
    
    private void addFocusListener() {
        ((JATextField) getComponent(1)).addFocusListener(
                new FocusListener() {

                    public void focusGained( FocusEvent e ) {
                    }

                    public void focusLost( FocusEvent e ) {
                        try {

                            if ( ((JATextField) getComponent(1)).getValue().equals("") ) {
                                modelo = null;
                                if (label != null)
                                    label.setText("");
                            }

                            if ( !((JATextField) getComponent(1)).getValue().equals("") ) {
                                if ( !objectIdClass.isAssignableFrom(Integer.class) ) {
                                    // Acertar SGT
                                    modelo = dao.consultar(((JATextField) getComponent(1)).intValue(), false);
                                } else {                                                                        
                                    modelo = dao.consultar(((JATextField) getComponent(1)).intValue(), false);
                                }
                            }

                        } catch (RuntimeException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Falha ao realizar consulta");
                        }
                        if ( !((JATextField) getComponent(1)).getValue().equals("") && modelo == null ) {
                            JOptionPane.showMessageDialog(null, "Registro não encontrado");
                            ((JATextField) getComponent(1)).setText("");
                            if ( label != null ) {
                                label.setText("");
                            }
                        } else {
                            if ( label != null && modelo != null ) {
                                label.setText(modelo.toString());
                            }
                        }
                    }
                });
    }

    private void openDialog() {
        if ( d instanceof DSelecionarObjeto ) {
            ((DSelecionarObjeto) d).open();
        } else {
            d.setVisible(true);
        }
    }

    private void addActionListener() {

        AbstractAction action = new AbstractAction("F4") {

            public void actionPerformed( ActionEvent e ) {
                openDialog();
            }
        };

        ((JButton) getComponent(0)).addActionListener(action);

        ((JTextField) getComponent(1)).addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked( MouseEvent e ) {
                if ( e.getClickCount() == 2 ) {
                    openDialog();
                }
                super.mouseClicked(e);
            }
        });

        ((JTextField) getComponent(1)).addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased( KeyEvent e ) {
                if ( e.getKeyCode() == KeyEvent.VK_F4 ) {
                    openDialog();
                }
                super.keyReleased(e);
            }
        });
    }


    @Override
    public void requestFocus() {
        this.getEditor().requestFocus();
    }        
}