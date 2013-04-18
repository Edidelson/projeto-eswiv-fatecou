/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Edidelson
 */
@Entity
@Table(name = "despesas")
@NamedQueries({
    @NamedQuery(name = "Despesas.getAll", query = "SELECT e FROM Despesas e")
})
public class Despesas implements Serializable, IModelo{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="cp_codigo")
    private int codigo;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ce_bem", nullable = false, insertable = true, updatable = true)
    private Bem bem;
    @Column(name="tx_descricao")
    private String descricao;
    @Column(name="vl_valor")
    private Double valor;
    @Column(name="dt_despesa")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDespesa;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Bem getBem() {
        return bem;
    }

    public void setBem(Bem bem) {
        this.bem = bem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(Date dataDespesa) {
        this.dataDespesa = dataDespesa;
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
