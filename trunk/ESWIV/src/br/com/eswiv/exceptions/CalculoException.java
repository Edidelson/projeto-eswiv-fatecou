/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.exceptions;

/**
 *
 * @author Edidelson
 */
public class CalculoException extends ArithmeticException {

    String MENSAGEM = "Ocorreu um erro inesperado, por favor verifique os valores do cálculo.";

    @Override
    public String getMessage() {
        return MENSAGEM;
    }
}
