/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Edidelson
 */
@Entity
@Table(name = "bem")
//@NamedQuery = responsável por controlar as HQL no banco
@NamedQueries({
    @NamedQuery(name = "Bem.getAll", query = "SELECT e FROM Bem e")
})
public class Bem implements Serializable, IModelo {

    @Id
    //@GeneratedValue = Auto increment 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cp_bem")
    private int codigo;
    //@ManyTOOne = um para muitos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ce_proprietario")
    private Proprietario proprietario;
    @Column(name = "tx_grupo")
    private String grupo;
    @Column(name = "tx_descricao")
    private String descricao;
    @Column(name = "dt_aquisicao")
    //@Temporal é utilizado quando for usar Data
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date aquisicao;
    @Column(name = "nu_turno")
    @Enumerated(value = EnumType.ORDINAL)
    private Turno turno;
    @Column(name = "vl_venal")
    private Double valorVenal;
    @Column(name = "nu_tipo")
    @Enumerated(EnumType.ORDINAL)
    private Tipo tipo;
    @Column(name="vl_depreciacao")
    private Double depreciacao;
     //@OneToMany = muitos para um
    //Cascade = Se um bem for removido, automaticamente remove todas as despesas
    //Fetch = Une o bem com a despesa
    //mappedBy = Ele vai buscar dentro da classe Despesa o nome do chave estrangeira, no caso "bem"
    //orphanRemoval = permite remover
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bem", orphanRemoval = true)
    private List<Despesas> despesas = new ArrayList<>();

    public Double getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(Double depreciacao) {
        this.depreciacao = depreciacao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Despesas> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesas> despesas) {
        this.despesas = despesas;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getAquisicao() {
        return aquisicao;
    }

    public void setAquisicao(Date aquisicao) {
        this.aquisicao = aquisicao;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Double getValorVenal() {
        return valorVenal;
    }

    public void setValorVenal(Double valorVenal) {
        this.valorVenal = valorVenal;
    }

    @Override
    public Object getCodigo() {
        return codigo;
    }

    @Override
    public boolean isInativo() {
        return false;
    }

    public static enum Turno {

        H8(1, "8 Horas"),
        H16(2, "16 horas"),
        H24(3, "24 horas");
        private int codigo;
        private String descricao;

        private Turno() {
        }

        private Turno(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
    }

    public static enum Tipo {

        NOVO,
        USADO
    }
}
