/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Edidelson
 */
@Embeddable
public class HistoricoPK implements Serializable{
    
    @Column(name="nu_sequencial")
    private int sequencial;
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ce_calculo", nullable=false, updatable=false,insertable=false)
    private Calculo calculo;

    public HistoricoPK() {
    }

    public HistoricoPK(int sequencial, Calculo calculo) {
        this.sequencial = sequencial;
        this.calculo = calculo;
    }

    public int getSequencial() {
        return sequencial;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.sequencial;
        hash = 43 * hash + Objects.hashCode(this.calculo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistoricoPK other = (HistoricoPK) obj;
        if (this.sequencial != other.sequencial) {
            return false;
        }
        if (!Objects.equals(this.calculo, other.calculo)) {
            return false;
        }
        return true;
    }
    
}
