/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Edidelson
 */
@Entity
@Table(name = "proprietario")
@NamedQueries({
    @NamedQuery(name = "Proprietario.getAll", query = "SELECT e FROM Proprietario e")
})
public class Proprietario implements Serializable, IModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cp_proprietario")
    private int codigo;
    @Column(name = "tx_nome")
    private String nome;
    @Column(name = "tx_email")
    private String email;
    @Column(name = "tx_telefone")
    private String telefone;
    @Column(name = "tx_celular")
    private String celular;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "dt_cadastro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_cadastro;
    @Embedded
    private Endereco endereco;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        telefone = telefone.replace("(", "");
        telefone = telefone.replace(")", "");
        this.telefone = telefone.length() == 0 ? null : telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        celular = celular.replace("(", "");
        celular = celular.replace(")", "");
        this.celular = celular.length() == 0 ? null : celular;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        this.cpf = cpf.length() == 0 ? null : cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return nome;
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
