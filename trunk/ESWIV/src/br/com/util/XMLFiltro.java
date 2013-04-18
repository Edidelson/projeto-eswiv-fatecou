package br.com.util;

/**
 *
 * @author Renato
 * @since 06/05/2011
 */
public class XMLFiltro {
    
    private String valorComparar;
    private String chaveCampoComparador;
    private String operador;
    private String comparador;
    
    public XMLFiltro() {       
    }

    public XMLFiltro(String valorComparar, String chaveCampoComparador, String operador, String comparador) {
        this.valorComparar = valorComparar;
        this.chaveCampoComparador = chaveCampoComparador;
        this.operador = operador;
        this.comparador = comparador;
    }   
    
    public String getChaveCampoComparador() {
        return chaveCampoComparador;
    }

    public void setChaveCampoComparador(String chaveCampoComparador) {
        this.chaveCampoComparador = chaveCampoComparador;
    }

    public String getComparador() {
        return comparador;
    }

    public void setComparador(String comparador) {
        this.comparador = comparador;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getValorComparar() {
        return valorComparar;
    }

    public void setValorComparar(String valorComparar) {
        this.valorComparar = valorComparar;
    }   
}