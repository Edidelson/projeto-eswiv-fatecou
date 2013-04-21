/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 
 */
@Embeddable
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
public class Contatos implements Serializable{

    @Column(name = "tx_nome_contato")
    private String nomeContato;
    @Column(name = "tx_email")
    private String email;
    @Column(name = "tx_telefone1")
    private String telefone1;
    @Column(name = "tx_telefone2")
    private String telefone2;
    @Column(name = "tx_celular")
    private String celular;

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
         // Removendo os caracteres especiais
        celular = celular.replace("(", "");
        celular = celular.replace(")", "");
        celular = celular.replace("-", "");
        celular = celular.replace(" ", "");
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
         // Removendo os caracteres especiais
//        telefone1 = telefone1.replace("(", "");
//        telefone1 = telefone1.replace(")", "");
//        telefone1 = telefone1.replace("-", "");
//        telefone1 = telefone1.replace(" ", "");
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
         // Removendo os caracteres especiais
        telefone2 = telefone2.replace("(", "");
        telefone2 = telefone2.replace(")", "");
        telefone2 = telefone2.replace("-", "");
        telefone2 = telefone2.replace(" ", "");
        this.telefone2 = telefone2;
    }
}
