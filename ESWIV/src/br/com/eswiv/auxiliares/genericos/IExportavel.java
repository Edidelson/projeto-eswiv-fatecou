/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.auxiliares.genericos;

import br.com.eswiv.modelo.Exportacao;

/**
 *
 * @author Everton
 * 
 * Interface de marcação para as entidades que poderão ser exportadas
 * para o Radar
 */
public interface IExportavel {
    
    /**
     * Obtem os dados da exportação
     * @return 
     */
    Exportacao getExportacao();
    /**
     * Configura a exportação
     */
    void setExportacao(Exportacao exportacao);
}
