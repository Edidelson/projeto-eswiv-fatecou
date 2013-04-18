/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.eswiv.tela.relatorio;

/**
 *
 * @author Everton
 *
 * Definição dos métodos fundamentais para as janelas de filtro para geração de relatórios
 */
public interface IFrameRelatorio {

    /**
     * Configura o elemento responsável por fornecer o tipo de ordenação a qual o relatório deverá seguir
     * @return
     */
    Object[] configurarOrdenacao();

    /**
     * Configura os parâmetros necessários para o relatório
     * e o gera, abrindo a janela do JasperViewer
     */
    void imprimir();
}
