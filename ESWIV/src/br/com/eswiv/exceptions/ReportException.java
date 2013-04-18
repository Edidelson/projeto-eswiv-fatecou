/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.eswiv.exceptions;

/**
 * DOCUMENTAÇÃO DA CLASSE
 * <br><br>
 * FINALIDADE:
 * <br>
 *
 * <br><br>
 * HISTÓRICO DE DESENVOLVIMENTO:
 * <br>
 * 28/03/2011 - @author Everton - Primeira versão
 *
 * <br><br>
 * LISTA DE CLASSES INTERNAS:
 * <br>
 *
 * <br><br>
 * ERROS CONHECIDOS:
 * <br>
 *
 * <br><br>
 */

public class ReportException extends Exception {

    @Override
    public String getMessage() {
        return "Falha ao gerar relatório";
    }
}
