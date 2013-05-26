/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Edidelson
 */
@Entity
@Table(name = "calculo")
@NamedQueries({
    @NamedQuery(name = "Calculo.getAll", query = "SELECT e FROM Calculo e ORDER BY e.codigo ASC"),
    @NamedQuery(name = "Calculo.findMax", query = "SELECT MAX(valorCalculo) FROM Calculo e WHERE e.bem=:codigo")
})
public class Calculo implements Serializable, IModelo{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cp_calculo")
    private int codigo;
    @Column(name = "dt_calculo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCalculo;
    @Column(name = "tx_observacao")
    private String observacao;
    @ManyToOne(fetch= FetchType.LAZY)
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
