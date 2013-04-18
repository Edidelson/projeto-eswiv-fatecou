
package br.com.eswiv.exceptions;

import java.awt.Component;

/**
 * Classe para tratar as exceções geradas pelo programa.<br><br>
 * @version    1.00, 07 Março 2011
 * @author  Edidelson
 */
public class CadastroException extends Exception {

    /**
     * Exceção gerada vai buscar no método qual é o erro.
     * 
     * @param tipoMessage qual é a mensagem que deve ser devolvida para o usuário.
     */
    public CadastroException(int tipoMessage) {
        super(getMsg(tipoMessage, null, null));
    }

    public CadastroException(String mensagem, Component c) {
        super(mensagem);
        c.requestFocus();
    }
    
    /**
     * Exceção gerada vai buscar no método qual é o erro.
     * 
     * @param tipoMessage qual é a mensagem que deve ser devolvida para o usuário.
     * @param campo caso queira que apareça na mensagem o nome do campo.
     */
    public CadastroException(int tipoMessage, String campo) {
        super(getMsg(tipoMessage, campo, null));
    }

    /**
     * Exceção gerada, passado por parâmetro o nome do erro.
     * 
     * @param mensagem nome do erro.
     */
    public CadastroException(String mensagem) {
        super(getMsg(0, null, mensagem));
    }

    /**
     * Exceção gerada, passado por parâmetro o nome do erro.
     * 
     * @param campo passa por parâmetro o campo para mostrar o erro.
     * @param mensagem nome do erro.
     */
    public CadastroException(String campo, String mensagem){
        super(getMsg(-1,campo,mensagem));
    }
    
    /**
     * Método responsável para vericar qual o tipo de mensagem retornar.
     * 
     * @param tipoMessagem qual é a mensagem que deve ser devolvida para o usuário.
     * @param campo caso queira que apareça na mensagem o nome do campo.
     * @param mensagem mensagem passada pelo usuário.
     * @return retorna uma string com a mensagem de erro.
     */
    public static String getMsg(int tipoMessagem, String campo, String mensagem) {

        if (tipoMessagem == -1) {
            return "O campo '"+ campo +"' " + mensagem;                    
        }
        
        if (tipoMessagem == 0) {
            return mensagem;
        }

        String msg;

        switch (tipoMessagem) {
            case 1:
                msg = "Nenhum nome foi informado, a rota não foi salva!";
                break;
            case 2:
                msg = "Valor não informado para o campo: " + campo;
                break;
            case 3:
                msg = "Não foi selecionado nenhum campo para alteração.";
                break;
            default:
                msg = "Ocorreu um erro inesperado, por favor entre em contato com o suporte.";
                break;
        }
        
        return msg;
    }
}