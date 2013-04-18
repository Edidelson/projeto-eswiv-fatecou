/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.tela.relatorio;

import br.com.util.XMLRelatorio;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Everton
 */
public abstract class Filtro extends FRelatorioGenerico {
    
    protected XMLRelatorio relatorio;
    protected Map<String, Component[]> componentes;
    protected Map<String, Component> obrigatorios;
    protected List<Component> camposObrigatorios;
    
    public void setRelatorio(XMLRelatorio relatorio) {
        this.relatorio = relatorio;
        camposObrigatorios = new ArrayList<Component>();
        if(relatorio.getComponentes() != null) {
            for(String componente: relatorio.getComponentes()) {
                if(componentes.get(componente) != null) {
                    for(Component c: componentes.get(componente)) {
                        c.setEnabled(true);
                    }
                }
            }
            if(relatorio.getObrigatorios() != null) {
                for(String s: relatorio.getObrigatorios()) {
                    camposObrigatorios.add(obrigatorios.get(s));
                }
            }
        }
        
        // Configurando o título do relatório
        if(relatorio != null) {
            setTitle(relatorio.getNome());
        }
    }
    
    /**
     * Verifica se os campos marcados no XML como obrigatórios foram corretamente
     * preenchidos
     * @param camposObrigatorios
     * @return 
     */
    protected boolean verificarCampos(List<Component> camposObrigatorios) {
        if(camposObrigatorios == null) {
            return true;
        }
        
        for(Component c: camposObrigatorios) {
            if(c instanceof JTextField) {
                if(((JTextField) c).getText().equals("")) {
                    c.requestFocus();
                    return false;
                }
            } else if(c instanceof JXDatePicker) {
                if(((JXDatePicker) c).getDate() == null) {
                    ((JXDatePicker) c).getComponent(0).requestFocus();
                    return false;
                }
            }
        }
        
        return true;
    }
}
