package br.com.util;

import java.io.File;
import java.util.HashMap;

/**
 * @author Renato
 * @since 03/05/2011
 */
public class XMLRelatorio {

    private String versao;
    private String grupo;
    private String modificadoEm;
    private String usuario;
    private String empresa;    
    private String nome;
    private String caminho;    
    private String campoOrdenacao;
    private String tipoOrdenacao;
    
    private File arquivo;
    private boolean padrao;
    private boolean filtroEditavel;
    
    private String[] componentes;
    private String[] obrigatorios;
             
    HashMap<Object, XMLFiltro> filtro = new HashMap<Object, XMLFiltro>();

    public XMLRelatorio(String versao, String grupo, String modificadoEm, String usuario, String empresa, String nome, String caminho, String campoOrdenacao, String tipoOrdenacao, File arquivo, boolean padrao, boolean filtroEditavel) {
        this.versao = versao;
        this.grupo = grupo;
        this.modificadoEm = modificadoEm;
        this.usuario = usuario;
        this.empresa = empresa;
        this.nome = nome;
        this.caminho = caminho;
        this.campoOrdenacao = campoOrdenacao;
        this.tipoOrdenacao = tipoOrdenacao;
        this.arquivo = arquivo;
        this.padrao = padrao;
        this.filtroEditavel = filtroEditavel;
    } 
    
    public XMLRelatorio(String versao, String grupo, String modificadoEm, String usuario, String empresa, String nome, String caminho, String componentes, String obrigatorios, String campoOrdenacao, String tipoOrdenacao, File arquivo, boolean padrao, boolean filtroEditavel) {
        this.versao = versao;
        this.grupo = grupo;
        this.modificadoEm = modificadoEm;
        this.usuario = usuario;
        this.empresa = empresa;
        this.nome = nome;
        this.caminho = caminho;
        this.campoOrdenacao = campoOrdenacao;
        this.tipoOrdenacao = tipoOrdenacao;
        this.arquivo = arquivo;
        this.padrao = padrao;
        this.filtroEditavel = filtroEditavel;
        if(componentes != null) {
            // Preparando a lista de componentes
            componentes = componentes.replace(" ", "");
            this.componentes = componentes.split(",");
        }
        if(obrigatorios != null) {
            // Preparando a lista de campos obrigatorios
            obrigatorios = obrigatorios.replace(" ", "");
            this.obrigatorios = obrigatorios.split(",");
        }
    }        
    
    public Object getValorPropriedade(Object key) {
        return filtro.get(key);
    }
    
    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }        
    
    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getModificadoEm() {
        return modificadoEm;
    }

    public void setModificadoEm(String modificadoEm) {
        this.modificadoEm = modificadoEm;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public HashMap<Object, XMLFiltro> getFiltro() {
        return filtro;
    }

    public void setFiltro(HashMap<Object, XMLFiltro> filtro) {
        this.filtro = filtro;
    }

    public boolean isFiltroEditavel() {
        return filtroEditavel;
    }

    public void setFiltroEditavel(boolean filtroEditavel) {
        this.filtroEditavel = filtroEditavel;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;    
    }

    public String getCampoOrdenacao() {
        return campoOrdenacao;
    }

    public void setCampoOrdenacao(String campoOrdenacao) {
        this.campoOrdenacao = campoOrdenacao;
    }

    public String getTipoOrdenacao() {
        return tipoOrdenacao;
    }

    public void setTipoOrdenacao(String tipoOrdenacao) {
        this.tipoOrdenacao = tipoOrdenacao;
    }

    public String[] getComponentes() {
        return componentes;
    }

    public void setComponentes(String[] componentes) {
        this.componentes = componentes;
    }

    public String[] getObrigatorios() {
        return obrigatorios;
    }

    public void setObrigatorios(String[] obrigatorios) {
        this.obrigatorios = obrigatorios;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}