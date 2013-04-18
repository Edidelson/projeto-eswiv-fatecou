/*
 * $Id: JAFrame.java 484 2012-04-12 12:52:22Z Renato $
 *
 * Copyright (c) 1998-2012 Zap Informática, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * ZapInfo Informática, Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Zap Informática.
 */
package br.com.eswiv.tela.generico;

import com.zap.arca.JADecimalFormatField;
import com.zap.arca.LoggerEx;
import com.zap.arca.util.WindowUtils;
import java.awt.Component;
import java.math.BigDecimal;
import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;

/**
 * Frame principal que estende as funcionalidades da classe JFrame base do Java,
 * incrementando características usadas no software. Utilidades:
 * <p/>
 * Controle de exceções não capturadas:
 * <pre>
 *      Thread.setDefaultUncaughtExceptionHandler(new LogExceptions());
 * </pre>
 * Inicialização do ícone padrão dos Frames. <p>
 * Escape automático da tela usando a tecla 'ESC'. <p>
 * Método para limpar os componentes inseridos no {@code Frame}.
 *
 * @since SGT 1.0
 * @version 11 Abril 2012
 *
 * @author Renato
 */
public class JAFrame extends javax.swing.JFrame {

    // Bloco de inicialização das funções padrão.
    {
        WindowUtils.setSystemLookAndFeel();
        WindowUtils.exitEsc(this);
        //WindowUtils.(this, "/resources/imagens/icone.png");
        Thread.setDefaultUncaughtExceptionHandler(
                new LogExceptions());
    }

    /**
     * Limpa os valores do componente de um JFrame.
     *
     * @param clear O primeiro componente do array é o que receberá o foco.
     * @param components Os próximos parâmetros são os componentes que
     * não devem ser limpos, para caso exista alguma condição para ele limpar.
     */
    public void clearFields( Component[] clear, Component... components ) {

        // Itera sobre os componentes verificando seus tipos e os limpando
        loop:
        for (Component c : clear) {

            if (components != null) {
                // Se foi passada um array com componentes que não devem ser
                // limpos, é feito um novo for que verifica se o componente
                // está contido nesse array, se sim usa o comando continue,
                // para ir para o próximo elemento do loop principal.
                for (Component d : components) {
                    if (c.equals(d)) {
                        continue loop;
                    }
                }
            }

            if (c instanceof JTextField) {
                JTextField tf = ( JTextField ) c;
                tf.setText("");
            } else if (c instanceof JADecimalFormatField) {
                JADecimalFormatField ftf = ( JADecimalFormatField ) c;
                ftf.setValue(BigDecimal.ZERO);
            } else if (c instanceof JADecimalFormatField) {
                JADecimalFormatField ftf = ( JADecimalFormatField ) c;
                ftf.setValue(BigDecimal.ZERO);
            } else if (c instanceof JCheckBox) {
                JCheckBox cb = ( JCheckBox ) c;
                cb.setSelected(false);
            } else if (c instanceof JLabel) {
                JLabel lb = ( JLabel ) c;
                lb.setText("");
            } else if (c instanceof JXDatePicker) {
                // Se for um JXDatePicker o configura com a data atual
                JXDatePicker dp = ( JXDatePicker ) c;
                dp.setDate(new java.util.Date());
            } else if (c instanceof JComboBox) {
                JComboBox cb = ( JComboBox ) c;
                cb.setSelectedIndex(0);
            } else if (c instanceof JPasswordField) {
                JPasswordField jp = ( JPasswordField ) c;
                jp.setText("");
            } else if (c instanceof JXSelectPicker) {
                JXSelectPicker jxsp = ( JXSelectPicker ) c;
                jxsp.setObjeto(null);
            }
        }
        // Por último o primeiro componente passado por parâmetro, recebe o foco.
        clear[0].requestFocus();
    }

    /**
     * Classe para tratamento de exceções não capturadas.
     * <p/>
     * Todas as exceções geradas pelo programa por Frames que estendem o
     * <code>FrameGenerico</code> que não estiverem em um bloco
     * <code>try{}, catch()</code> serão lançadas para esta classe no método
     * <code>uncaughtException()</code>.
     */
    public static class LogExceptions implements Thread.UncaughtExceptionHandler {

        public void uncaughtException( Thread t, Throwable e ) {
            LoggerEx.log(e);
            e.printStackTrace();
        }
    }
}