/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Edidelson
 */
@Entity
@Table(name = "historico")
public class Historico implements Serializable, IModelo, Cloneable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name="sequencial", column=@Column(name="nu_sequencial", nullable=false)),
        @AttributeOverride(name="calculo", column=@Column(name="ce_calculo", nullable=false)),
    })
    private HistoricoPK pK;
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ce_calculo", updatable = false, insertable = false)
    private Calculo calculo;

    public HistoricoPK getpK() {
        return pK;
    }

    public void setpK(HistoricoPK pK) {
        this.pK = pK;
    }

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }

    @Override
    public  Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Historico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Object getCodigo() {
        return pK;
    }

    @Override
    public boolean isInativo() {
        return false;
    }
}