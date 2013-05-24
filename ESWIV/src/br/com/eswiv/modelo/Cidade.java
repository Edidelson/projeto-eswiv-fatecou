/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Edidelson
 */
@Entity
@Table(name="cidades")
@NamedQueries({
        @NamedQuery(name="Cidade.getAll", query="SELECT e FROM Cidade e ORDER BY e.codigo ASC")
    })
public class Cidade implements Serializable, IModelo, Cloneable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="cp_cidade")   
    private int codigo;
    
    @Column(name="tx_nome")
    private String nome;
    
    @Column(name="tx_estado")
    private String estado;
    
    @Column(name="tx_ddd")
    private String ddd;
    
    @Column(name="qt_distancia")
    private Double distancia;
    
    @Column(name="nr_ibge")
    private int ibge;
    
    @Override
    public Object getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIbge() {
        return ibge;
    }

    public void setIbge(int ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
    @Override
    public boolean isInativo() {
      return false;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
