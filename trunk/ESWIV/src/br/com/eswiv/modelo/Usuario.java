package br.com.eswiv.modelo;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 * DOCUMENTAÇÃO DA CLASSE
 * <br><br>
 * FINALIDADE:
 * <br>
 * Entidade Usuário
 * <br><br>
 * HISTÓRICO DE DESENVOLVIMENTO:
 * <br>
 * 10/01/2011 - @author Edidelson - Primeira versão
 *
 * <br><br>
 * LISTA DE CLASSES INTERNAS:
 * <br>
 *
 * <br><br>
 * ERROS CONHECIDOS:
 * <br>
 *
 * <br><br>
 */
@Entity
@Table(name="usuarios")
@NamedQueries({
    @NamedQuery(name="Usuario.getAll", query="SELECT u FROM Usuario u ORDER BY u.codigo ASC"),
    @NamedQuery(name="Usuario.verificarApelido", query="SELECT u FROM Usuario u WHERE u.apelido = :apelido"),
    @NamedQuery(name="Usuario.autenticar", query="SELECT u FROM Usuario u WHERE UPPER(u.apelido) = :apelido AND u.senha = :senha AND u.inativo = false")
})
public class Usuario implements IModelo, Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cp_usuario")
    private int codigo;
    @Column(name="tx_nome")
    private String nome;
    @Column(name="tx_apelido")
    private String apelido;
    @Column(name="tx_senha")
    private String senha;
    @Column(name="dt_inclusao")
    private Date dataInclusao;
    @Column(name="fg_inativo")
    private boolean inativo;
    @Column(name="fg_permissao")
    private boolean supervisor;

    public String getApelido() {
        return apelido;
    }

    public boolean isSupervisor() {
        return supervisor;
    }

    public void setSupervisor(boolean supervisor) {
        this.supervisor = supervisor;
    }

    
    @Override
    public Object getCodigo() {
        return codigo;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean isInativo() {
        return this.inativo;
    }
    
    /**
     * Texto descritivo para o objeto
     * @return String - representação do objeto como um texto
     */
    @Override
    public String toString() {
        return nome;
    }
}
