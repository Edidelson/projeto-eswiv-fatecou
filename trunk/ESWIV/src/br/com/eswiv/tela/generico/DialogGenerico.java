package br.com.eswiv.tela.generico;

import br.com.util.Util;
import com.zap.arca.util.WindowUtils;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Renato
 */
public class DialogGenerico extends javax.swing.JDialog {
    
    private Util util;
        
    {
        util = Util.getUtil();
        WindowUtils.setSystemLookAndFeel();
//        util.inserirIconeAplicacao(this);
    }

    public DialogGenerico() {
    }

    public DialogGenerico( Dialog owner ) {
        super(owner);
    }

    public DialogGenerico( Frame owner ) {
        super(owner);
    }

    public DialogGenerico( Window owner ) {
        super(owner);
    }

    public DialogGenerico( Dialog owner, String title ) {
        super(owner, title);
    }

    public DialogGenerico( Dialog owner, boolean modal ) {
        super(owner, modal);
    }

    public DialogGenerico( Frame owner, String title ) {
        super(owner, title);
    }

    public DialogGenerico( Frame owner, boolean modal ) {
        super(owner, modal);
    }

    public DialogGenerico( Window owner, ModalityType modalityType ) {
        super(owner, modalityType);
    }

    public DialogGenerico( Window owner, String title ) {
        super(owner, title);
    }

    public DialogGenerico( Dialog owner, String title, boolean modal ) {
        super(owner, title, modal);
    }

    public DialogGenerico( Frame owner, String title, boolean modal ) {
        super(owner, title, modal);
    }

    public DialogGenerico( Window owner, String title, ModalityType modalityType ) {
        super(owner, title, modalityType);
    }

    public DialogGenerico( Dialog owner, String title, boolean modal, GraphicsConfiguration gc ) {
        super(owner, title, modal, gc);
    }

    public DialogGenerico( Frame owner, String title, boolean modal, GraphicsConfiguration gc ) {
        super(owner, title, modal, gc);
    }

    public DialogGenerico( Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc ) {
        super(owner, title, modalityType, gc);
    }
        
    /**
     * 
     * @return
     */
    public JDialog getDialog() {
        return this;
    }
    
    @Override
    protected JRootPane createRootPane() {
        ActionListener actionListener = new ActionListener()  {

            public void actionPerformed(ActionEvent actionEvent) {
                dispatchEvent(new WindowEvent(getDialog(),
                        WindowEvent.WINDOW_CLOSING));
            }
        };
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
}