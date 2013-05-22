/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
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
@Table(name = "calculo")
@NamedQueries({
    @NamedQuery(name = "Calculo.getAll", query = "SELECT e FROM Calculo e"),
    @NamedQuery(name = "Calculo.findMax", query = "SELECT MAX(acumulado) FROM Calculo e WHERE e.bem=:codigo")
})
public class Calculo implements Serializable, IModelo{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cp_calculo")
    private int codigo;
    @Column(name = "vl_acumulado")
    private BigDecimal acumulado;
    @Column(name = "dt_calculo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCalculo;
    @Column(name = "tx_observacao")
    private String observacao;
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ce_bem")
    private Bem bem;
    @Column(name="vl_calculo")
    private Double valorCalculo;

    public Double getValorCalculo() {
        return valorCalculo;
    }

    public void setValorCalculo(Double valorCalculo) {
        this.valorCalculo = valorCalculo;
    }
    
    public Bem getBem() {
        return bem;
    }

    public void setBem(Bem bem) {
        this.bem = bem;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(BigDecimal acumulado) {
        this.acumulado = acumulado;
    }

    public Date getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(Date dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
