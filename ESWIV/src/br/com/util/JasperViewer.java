/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */

/*
 * Contributors:
 * Ryan Johnson - delscovich@users.sourceforge.net
 * Carlton Moore - cmoore79@users.sourceforge.net
 */
package br.com.util;

import com.zap.arca.JAEmail;
import com.zap.arca.mail.Anexo;
import com.zap.arca.mail.Mail;
import com.zap.arca.util.WindowUtils;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRClassLoader;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.save.JRPrintSaveContributor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * $Revision:: 475             $: <br>
 * $Date:: 2012-04-10 17:05:31#$: <br>
 * $Author:: Renato            $: <br>
 *  
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JasperViewer.java 475 2012-04-10 20:05:31Z Renato $
 */
public class JasperViewer extends javax.swing.JFrame {

    private long fileMail;    
    private Mail mail;
    
    {
        WindowUtils.setSystemLookAndFeel();
        this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
    }
    /**
     *
     */
    private JRViewer viewer = null;
    /**
     *
     */
    private boolean isExitOnClose = true;

    /** Creates new form JasperViewer */
    public JasperViewer(
            String sourceFile,
            boolean isXMLFile) throws JRException {
        this(sourceFile, isXMLFile, true);
    }

    /** Creates new form JasperViewer */
    public JasperViewer(
            InputStream is,
            boolean isXMLFile) throws JRException {
        this(is, isXMLFile, true);
    }

    /** Creates new form JasperViewer */
    public JasperViewer(
            JasperPrint jasperPrint) {
        this(jasperPrint, true);
    }

    /** Creates new form JasperViewer */
    public JasperViewer(
            String sourceFile,
            boolean isXMLFile,
            boolean isExitOnClose) throws JRException {
        this(sourceFile, isXMLFile, isExitOnClose, null);
    }

    /** Creates new form JasperViewer */
    public JasperViewer(
            InputStream is,
            boolean isXMLFile,
            boolean isExitOnClose) throws JRException {
        this(is, isXMLFile, isExitOnClose, null);
    }

    /** Creates new form JasperViewer */
    public JasperViewer(
            JasperPrint jasperPrint,
            boolean isExitOnClose) {
        this(jasperPrint, isExitOnClose, null);
    }

    /** Creates new form JasperViewer */
    public JasperViewer(
            String sourceFile,
            boolean isXMLFile,
            boolean isExitOnClose,
            Locale locale) throws JRException {
        if (locale != null) {
            setLocale(locale);
        }

        this.isExitOnClose = isExitOnClose;

        initComponents();

        this.viewer = new JRViewer(sourceFile, isXMLFile, locale);
        this.pnlMain.add(viewer, BorderLayout.CENTER);

    }

    /** Creates new form JasperViewer */
    public JasperViewer(
            InputStream is,
            boolean isXMLFile,
            boolean isExitOnClose,
            Locale locale) throws JRException {
        if (locale != null) {
            setLocale(locale);
        }

        this.isExitOnClose = isExitOnClose;

        initComponents();

        this.viewer = new JRViewer(is, isXMLFile, locale);
        this.pnlMain.add(this.viewer, BorderLayout.CENTER);
    }

    /** Creates new form JasperViewer */
    public JasperViewer(
            JasperPrint jasperPrint,
            boolean isExitOnClose,
            Locale locale) {
        if (locale != null) {
            setLocale(locale);
        }

        this.isExitOnClose = isExitOnClose;

        initComponents();

        // Maximizando o formulário ao iniciar
        this.setExtendedState(MAXIMIZED_BOTH);
        //this.setAlwaysOnTop(true);

        this.viewer = new JRViewer(jasperPrint, locale);

        final JRViewer jrViewer = this.viewer;
        JPanel tlbToolBar = (JPanel) jrViewer.getComponent(0); // botões da barra superior                           
        
        //JButton btnSalvar = (JButton)pnlBotoes.getComponent(0);        
        //System.out.println(btnSalvar);        

        JButton btnGerarPDF = new JButton(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/_pdf.gif")));
        JButton btnGerarXLS = new JButton(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/page_white_excel.png")));
        JButton btnEnviarEmail = new JButton(new javax.swing.ImageIcon(getClass().getResource("/resources/imagens/email_go.png")));
        //btnGerarPDF.setBounds(1, 1, 2, 2);
        //pnlBotoes.setLayout(null);       

        this.jasperPrint = jasperPrint;
        //this.saveContributors.addAll(Arrays.asList(jrViewer.getSaveContributors()));        

        btnGerarPDF.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnGerarPDF.setMaximumSize(new java.awt.Dimension(23, 23));
        btnGerarPDF.setMinimumSize(new java.awt.Dimension(23, 23));
        btnGerarPDF.setPreferredSize(new java.awt.Dimension(23, 23));
        btnGerarPDF.setToolTipText("Gerar PDF");
        btnGerarPDF.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveContributors.clear();
                btnSaveActionPerformed(evt, new String[]{"net.sf.jasperreports.view.save.JRPdfSaveContributor"});
            }
        });

        btnGerarXLS.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnGerarXLS.setMaximumSize(new java.awt.Dimension(23, 23));
        btnGerarXLS.setMinimumSize(new java.awt.Dimension(23, 23));
        btnGerarXLS.setPreferredSize(new java.awt.Dimension(23, 23));
        btnGerarXLS.setToolTipText("Gerar para Excel");
        btnGerarXLS.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveContributors.clear();
                btnSaveActionPerformed(evt, new String[]{
                            "net.sf.jasperreports.view.save.JRSingleSheetXlsSaveContributor",
                            "net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor"
                        });
            }
        });

        btnEnviarEmail.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnEnviarEmail.setMaximumSize(new java.awt.Dimension(23, 23));
        btnEnviarEmail.setMinimumSize(new java.awt.Dimension(23, 23));
        btnEnviarEmail.setPreferredSize(new java.awt.Dimension(23, 23));
        btnEnviarEmail.setToolTipText("Enviar por Email");
        btnEnviarEmail.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarEmail();
            }
        });

        JPanel pnlSep01 = new javax.swing.JPanel();
        pnlSep01.setMaximumSize(new java.awt.Dimension(10, 10));
                
        tlbToolBar.add(pnlSep01, 3);
        tlbToolBar.add(btnGerarPDF, 4);
        tlbToolBar.add(btnGerarXLS, 5);
        tlbToolBar.add(btnEnviarEmail, 6);        
        
        //((JButton)((JPanel)viewer.getComponent(0)).getComponent(0)).setBounds(50, 50, 50, 50);
        //System.out.println(((JPanel)viewer.getComponent(0)).getComponent(0));
        this.pnlMain.add(jrViewer, BorderLayout.CENTER);
    }
    protected List saveContributors = new ArrayList();
    protected File lastFolder = null;
    protected JRSaveContributor lastSaveContributor = null;
    private ResourceBundle resourceBundle = null;
    private static final Log log = LogFactory.getLog(JRViewer.class);
    JasperPrint jasperPrint = null;

    private void initSaveContributors(final String[] DEFAULT_CONTRIBUTORS) {
        for (int i = 0; i < DEFAULT_CONTRIBUTORS.length; i++) {
            try {
                Class saveContribClass = JRClassLoader.loadClassForName(DEFAULT_CONTRIBUTORS[i]);
                Constructor constructor = saveContribClass.getConstructor(new Class[]{Locale.class, ResourceBundle.class});
                JRSaveContributor saveContrib = (JRSaveContributor) constructor.newInstance(new Object[]{getLocale(), resourceBundle});
                saveContributors.add(saveContrib);
            } catch (Exception e) {
            }
        }
    }   
    
    void enviarEmail() {
        if (mail == null)
            mail = new Mail();
        save(new String[]{"net.sf.jasperreports.view.save.JRPdfSaveContributor"});
        mail.getFileMails().clear();
        mail.addAnexo(new Anexo("C:\\Windows\\Temp\\Relatorio " + fileMail + ".pdf", true));
        new JAEmail(mail).setVisible(true);        
    }
    
    void setMail(Mail mail) {
        this.mail = mail;
    }

    void save(String[] contributors) {
        
        initSaveContributors(contributors);
     
        Random random = new Random(System.currentTimeMillis());
        this.fileMail = Math.abs(random.nextLong() % 1000);
        
        File file = new File("C:\\Windows\\Temp\\Relatorio " + fileMail);

        lastFolder = file.getParentFile();

        JRSaveContributor contributor = (JRSaveContributor) saveContributors.get(0);

        try {
            contributor.save(jasperPrint, file);
        } catch (JRException e) {
            if (log.isErrorEnabled()) {
                log.error("Save error.", e);
            }

            JOptionPane.showMessageDialog(this, getBundleString("error.saving"));
        }
    }

    void btnSaveActionPerformed(java.awt.event.ActionEvent evt, String[] contributors) {//GEN-FIRST:event_btnSaveActionPerformed        

        initSaveContributors(contributors);

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setLocale(this.getLocale());

        fileChooser.updateUI();

        for (int i = 0; i < saveContributors.size(); i++) {
            fileChooser.addChoosableFileFilter((JRSaveContributor) saveContributors.get(i));
        }

        if (saveContributors.contains(lastSaveContributor)) {
            fileChooser.setFileFilter(lastSaveContributor);
        } else if (saveContributors.size() > 0) {
            fileChooser.setFileFilter((JRSaveContributor) saveContributors.get(0));
        }

        if (lastFolder != null) {
            fileChooser.setCurrentDirectory(lastFolder);
        }

        int retValue = fileChooser.showSaveDialog(this);

        if (retValue == JFileChooser.APPROVE_OPTION) {

            FileFilter fileFilter = fileChooser.getFileFilter();

            File file = fileChooser.getSelectedFile();

            lastFolder = file.getParentFile();

            JRSaveContributor contributor = null;

            if (fileFilter instanceof JRSaveContributor) {
                contributor = (JRSaveContributor) fileFilter;
            } else {
                int i = 0;
                while (contributor == null && i < saveContributors.size()) {
                    contributor = (JRSaveContributor) saveContributors.get(i++);
                    if (!contributor.accept(file)) {
                        contributor = null;
                    }
                }

                if (contributor == null) {
                    contributor = new JRPrintSaveContributor(getLocale(), this.resourceBundle);
                }
            }

            lastSaveContributor = contributor;

            try {
                contributor.save(jasperPrint, file);
            } catch (JRException e) {
                if (log.isErrorEnabled()) {
                    log.error("Save error.", e);
                }

                JOptionPane.showMessageDialog(this, getBundleString("error.saving"));
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    protected String getBundleString(String key) {
        return resourceBundle.getString(key);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        pnlMain = new javax.swing.JPanel();
        //JButton button = new JButton("TESTE");
        setTitle("Visualizador de Relatórios");
        //setIconImage(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/jricon.GIF")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm();
            }
        });

        pnlMain.setLayout(new java.awt.BorderLayout());

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();

        Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = toolkit.getScreenSize();
        int screenResolution = toolkit.getScreenResolution();
        float zoom = ((float) screenResolution) / JRViewer.REPORT_RESOLUTION;

        int height = (int) (550 * zoom);
        if (height > screenSize.getHeight()) {
            height = (int) screenSize.getHeight();
        }
        int width = (int) (750 * zoom);
        if (width > screenSize.getWidth()) {
            width = (int) screenSize.getWidth();
        }

        java.awt.Dimension dimension = new java.awt.Dimension(width, height);
        setSize(dimension);
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
    }//GEN-END:initComponents

    /** Exit the Application */
    void exitForm() {//GEN-FIRST:event_exitForm

        if (this.isExitOnClose) {
            System.exit(0);
        } else {
            this.setVisible(false);
            this.viewer.clear();
            this.viewer = null;
            this.getContentPane().removeAll();
            this.dispose();
        }

    }//GEN-LAST:event_exitForm

    /**
     *
     */
    public void setZoomRatio(float zoomRatio) {
        viewer.setZoomRatio(zoomRatio);
    }

    /**
     *
     */
    public void setFitWidthZoomRatio() {
        viewer.setFitWidthZoomRatio();
    }

    /**
     *
     */
    public void setFitPageZoomRatio() {
        viewer.setFitPageZoomRatio();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        String fileName = null;
        boolean isXMLFile = false;

        if (args.length == 0) {
            usage();
            return;
        }

        int k = 0;
        while (args.length > k) {
            if (args[k].startsWith("-F")) {
                fileName = args[k].substring(2);
            }
            if (args[k].startsWith("-XML")) {
                isXMLFile = true;
            }

            k++;
        }

        try {
            viewReport(fileName, isXMLFile);
        } catch (JRException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     *
     */
    private static void usage() {
        System.out.println("JasperViewer usage:");
        System.out.println("\tjava JasperViewer -XML -Ffile");
    }

    /**
     *
     */
    public static void viewReport(
            String sourceFile,
            boolean isXMLFile) throws JRException {
        viewReport(sourceFile, isXMLFile, true, null);
    }

    /**
     *
     */
    public static void viewReport(
            InputStream is,
            boolean isXMLFile) throws JRException {
        viewReport(is, isXMLFile, true, null);
    }

    /**
     *
     */
    public static void viewReport(
            JasperPrint jasperPrint) {
        viewReport(jasperPrint, true, null);
    }

    /**
     *
     */
    public static void viewReport(
            String sourceFile,
            boolean isXMLFile,
            boolean isExitOnClose) throws JRException {
        viewReport(sourceFile, isXMLFile, isExitOnClose, null);
    }

    /**
     *
     */
    public static void viewReport(
            InputStream is,
            boolean isXMLFile,
            boolean isExitOnClose) throws JRException {
        viewReport(is, isXMLFile, isExitOnClose, null);
    }

    /**
     *
     */
    public static void viewReport(
            JasperPrint jasperPrint,
            boolean isExitOnClose) {
        viewReport(jasperPrint, isExitOnClose, null);
    }

    /**
     *
     */
    public static void viewReport(
            String sourceFile,
            boolean isXMLFile,
            boolean isExitOnClose,
            Locale locale) throws JRException {
        JasperViewer jasperViewer =
                new JasperViewer(
                sourceFile,
                isXMLFile,
                isExitOnClose,
                locale);
        jasperViewer.setVisible(true);
    }

    /**
     *
     */
    public static void viewReport(
            InputStream is,
            boolean isXMLFile,
            boolean isExitOnClose,
            Locale locale) throws JRException {
        JasperViewer jasperViewer =
                new JasperViewer(
                is,
                isXMLFile,
                isExitOnClose,
                locale);
        jasperViewer.setVisible(true);
    }

    /**
     *
     */
    public static void viewReport(
            JasperPrint jasperPrint,
            boolean isExitOnClose,
            Locale locale) {
        JasperViewer jasperViewer =
                new JasperViewer(
                jasperPrint,
                isExitOnClose,
                locale);
        jasperViewer.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}