/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Edidelson
 */
@Entity
@Table(name="proprietario")
@NamedQueries({
    @NamedQuery(name="Proprietario.getAll", query="SELECT e FROM Proprietario e")
})
public class Proprietario implements Serializable, IModelo{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="cp_proprietario")
    private int codigo;
    @Column(name="tx_nome")
    private String nome;
    @Column(name="tx_observacao")
    private String observacao;
    @Column(name="tx_telefone")
    private String telefone;
    @Column(name="tx_celular")
    private String celular;
    @Embedded
    private Endereco endereco;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    @Override
    public Object getCodigo() {
        return codigo;
    }

    @Override
    public boolean isInativo() {
        return false;
    }
    
}
