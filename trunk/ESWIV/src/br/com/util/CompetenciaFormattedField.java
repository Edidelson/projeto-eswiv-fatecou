
package br.com.util;

import com.zap.arca.JAFormattedTextField;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Edidelson
 */
public class CompetenciaFormattedField extends JAFormattedTextField {
    
    public CompetenciaFormattedField() {
        super(7);
        try {
            setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                    new javax.swing.text.MaskFormatter("##/####")));
            setFocusLostBehavior(COMMIT);
            setPreferredSize(new Dimension(80, 20));
            configurarEventos();
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Retorna um Date representando a competência informada
     * @return 
     */
    public Date getCompetencia() {
        return obterData();
    }
    
    /**
     * Configura a competência para ser exibida no campo
     * @param comp 
     */
    public void setCompetencia(Date comp) {
        if(comp != null) {
            SimpleDateFormat df = new SimpleDateFormat("MMyyyy");
            setText(df.format(comp));
        } else {
            setText(null);
        }
    }
    
    /**
     * Obtem uma data que representa o mês de pagamento
     * @return 
     */
    private Date obterData() {
        if(getText().isEmpty()) {
            return null;
        }
        
        String[] mesPagamento = getText().split("/");
        mesPagamento[0] = mesPagamento[0].trim();
        mesPagamento[1] = mesPagamento[1].trim();
        
        if(mesPagamento[0].isEmpty() || mesPagamento[1].isEmpty()) {
            return null;
        }
        
        int mes = Integer.parseInt(mesPagamento[0]);
        if(mes > 12 || mes < 1) {
            return null;
        }
        
        int ano = Integer.parseInt(mesPagamento[1]);
        if(ano > 3000 || ano < 1900) {
            return null;
        }
        
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, mes -1);
        c.set(Calendar.YEAR, ano);
        
        return c.getTime();
    }
    
    /**
     * Configura os eventos padrão do componente:
     * 
     * - Ao perder o foco testa se texto preenchido é válido.
     *   Caso não for, limpa o componente.
     */
    private void configurarEventos() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                setCompetencia(obterData());
            }
        });
    }
}