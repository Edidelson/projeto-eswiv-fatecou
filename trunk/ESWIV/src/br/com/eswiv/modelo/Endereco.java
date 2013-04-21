/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package br.com.eswiv.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Edidelson
 */
@Embeddable
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
public class Endereco implements Serializable {

    @Column(name = "tx_logradouro")
    private String logradouro;
    @Column(name = "nr_numero")
    private String numero;
    @Column(name = "tx_complemento")
    private String complemento;
    @Column(name = "tx_bairro")
    private String bairro;
    @Column(name = "tx_cep")
    private String cep;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ce_cidade")
    private Cidade cidade;

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro.length() == 0 ? null : bairro;
    }

    public void setCep(String cep) {
        // Removendo os caracteres especiais
        cep = cep.replace("-", "");
        cep = cep.replace(".", "");
        this.cep = cep.length() == 0 ? null : cep;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento.length() == 0 ? null : complemento;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro.length() == 0 ? null : logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero.length() == 0 ? null : numero;
    }
    

    @Override
    public String toString() {
        StringBuilder endereco = new StringBuilder(logradouro);
        endereco.append(", nº");
        endereco.append(numero);
        endereco.append(". ");
        endereco.append(bairro);
        endereco.append(", ");
        endereco.append(Endereco.formatarCep(cep));
        endereco.append(" - ");
        endereco.append(cidade.getNome());
        endereco.append("/ ");
        endereco.append(cidade.getEstado());
        return endereco.toString();
    }

    /**
     * Formata um CEP
     *
     * @param cep
     * @return
     */
    public static String formatarCep(String cep) {
        if (cep == null) {
            return "";
        }
        if (cep.length() != 8) {
            return cep;
        }

        String cepFormatado = cep;
        if (cepFormatado.length() == 8) {
            cepFormatado = cep.substring(0, 5) + "-" + cep.substring(5, 8);
        }
        return cepFormatado;
    }
}
