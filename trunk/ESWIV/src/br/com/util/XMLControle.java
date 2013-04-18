package br.com.util;

import com.zap.arca.LoggerEx;
import com.zap.arca.io.DirPoolingController;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Classe para controlar o XML.
 * @author Renato
 * @since 03/05/2011 
 */
public class XMLControle {

    /**
     * Exclui um arquivo
     * @param source diretorio do arquivo
     */
    public void excluirRelatorio(XMLRelatorio xmlRelatorio) {
        try {
            xmlRelatorio.getArquivo().delete();
        } catch (Exception ex) {
            LoggerEx.log(ex);
        }
    }

    public void criarOuAlterarRelatorio(XMLRelatorio xmlRelatorio) {

        try {

            Element grupo = new Element("grupo");
            grupo.setAttribute("versao", xmlRelatorio.getVersao());
            grupo.setAttribute("grupo", xmlRelatorio.getGrupo());
            grupo.setAttribute("modificadoEm", xmlRelatorio.getModificadoEm());
            grupo.setAttribute("usuario", xmlRelatorio.getUsuario());
            grupo.setAttribute("empresa", xmlRelatorio.getEmpresa());
            Document doc = new Document(grupo);

            Element relatorio = new Element("relatorio");
            relatorio.setAttribute("nome", xmlRelatorio.getNome());
            relatorio.setAttribute("caminho", xmlRelatorio.getCaminho());
            relatorio.setAttribute("padrao", String.valueOf(xmlRelatorio.isPadrao()));

            Element filtros = new Element("filtros");
            filtros.setAttribute("editavel", String.valueOf(xmlRelatorio.isFiltroEditavel()));
            filtros.setAttribute("campoOrdenacao", xmlRelatorio.getCampoOrdenacao());
            filtros.setAttribute("tipoOrdenacao", xmlRelatorio.getTipoOrdenacao());

            grupo.addContent(relatorio);
            relatorio.addContent(filtros);

            for (Map.Entry<Object, XMLFiltro> index : xmlRelatorio.getFiltro().entrySet()) {
                XMLFiltro filtro = index.getValue();
                Element campo = new Element("campo");
                campo.setAttribute("chave", String.valueOf(index.getKey()));
                campo.setAttribute("valorComparar", filtro.getValorComparar());
                campo.setAttribute("chaveCampoComparador", filtro.getChaveCampoComparador());
                campo.setAttribute("operador", filtro.getOperador());
                campo.setAttribute("comparador", filtro.getComparador());
                filtros.addContent(campo);
            }

            Format format = Format.getPrettyFormat();
            format.setOmitDeclaration(false);
            format.setOmitEncoding(false);
            format.setEncoding("ISO-8859-1");
            format.setLineSeparator("\n");

            XMLOutputter xout = new XMLOutputter(format);
            //xout.output(doc, System.out);

            //Writer out = new BufferedWriter(
            //      new OutputStreamWriter(new FileOutputStream("pedidos.xml", false), "ISO-8859-1"));

            //xout.output(doc, out);

            String des = xout.outputString(doc);

            // Modificando o arquivo original usando FileWriter.          
            FileWriter fileWriter = new FileWriter(xmlRelatorio.getArquivo());
            fileWriter.write(des);
            fileWriter.close();

        } catch (Exception ex) {
            LoggerEx.log(ex);
        }
    }

    /**
     * Método para atualizar o arquivo XML com o nome relatório padrão.     
     * @param relatorios deve conter uma lista de XMLRelatorio.
     */
    public void atualizarRelatorioPadrao(List<XMLRelatorio> relatorios) {

        try {

            // Faz um loop nos itens de relatorio...
            for (XMLRelatorio item : relatorios) {

                SAXBuilder sb = new SAXBuilder();
                Document document = sb.build(item.getArquivo());

                Element root = document.getRootElement();

                List elements = root.getChildren();

                // Cria um format que contém as definições de formatação do arquivo XML.
                Format format = Format.getPrettyFormat();
                format.setOmitDeclaration(false);
                format.setOmitEncoding(false);
                format.setEncoding("ISO-8859-1");
                format.setLineSeparator("\n");

                // Cria um objeto de saída do conteúdo do XML, passando o formato como parâmetro.
                XMLOutputter xout = new XMLOutputter(format);

                // Faz um loop obtendo os elementos filhos da tag...
                for (Object i : elements) {
                    // converte o objeto lido em um element...
                    Element e = (Element) i;
                    // altera o atributo de acordo com o novo o valor padrao do item.
                    e.getAttribute("padrao").setValue(String.valueOf(item.isPadrao()));
                }

                // Escreve para uma string os valores.
                String des = xout.outputString(document);

                // Modificando o arquivo original usando FileWriter.          
                FileWriter fileWriter = new FileWriter(item.getArquivo());
                fileWriter.write(des);
                fileWriter.close();
            }
        } catch (Exception ex) {
            LoggerEx.log(ex);
        }
    }

    /**
     * Método que retorna os dados de um relatório.    
     * @param nome_arquivo
     * @return retorna um objeto de XMLRelatorio.
     */
    public XMLRelatorio getRelatorio(String nome_arquivo) {

        File f = new File("relatorio/XML\\" + nome_arquivo + ".xml");

        XMLRelatorio xmlRelatorio = null;
        XMLFiltro filtro = null;

        try {

            // SAXBuilder que vai processar o XML.
            SAXBuilder sb = new SAXBuilder();
            // Passa a estrutura do arquivo XML para o documento.
            Document doc = sb.build(f);
            // Recuperamos o element principal.
            Element root = doc.getRootElement();
            // Recuperamos os elementos filhos (children).
            List elements = root.getChildren();

            // Faz um loop obtendo os elementos filhos da tag...
            for (Object i : elements) {
                // converte o objeto lido em um element...
                Element e = (Element) i;

                // faz um outro loop para obter os elementos filhos...
                for (Object i2 : e.getChildren()) {
                    Element e2 = (Element) i2;
                    
                    // criando uma instancia com os valores lidos...                    
                    xmlRelatorio = new XMLRelatorio(root.getAttributeValue("versao"), root.getAttributeValue("grupo"),
                                                    root.getAttributeValue("modificadoEm"), root.getAttributeValue("usuario"),
                                                    root.getAttributeValue("empresa"), e.getAttributeValue("nome"),
                                                    e.getAttributeValue("caminho"), e.getAttributeValue("componentes"), e.getAttributeValue("obrigatorios"),
                                                    e.getAttributeValue("campoOrdenacao"),
                                                    e.getAttributeValue("tipoOrdenacao"), f,
                                                    Boolean.valueOf(e.getAttributeValue("padrao")),
                                                    Boolean.valueOf(e2.getAttributeValue("editavel")));

                    for (Object i3 : e2.getChildren()) {
                        Element e3 = (Element) i3;

                        // cria um novo filtro para cada chave...
                        filtro = new XMLFiltro(e3.getAttributeValue("valorComparar"),
                                               e3.getAttributeValue("chaveValorComparador"),
                                               e3.getAttributeValue("operador"),
                                               e3.getAttributeValue("comparador"));

                        // adiciona as propriedades do filtro para o objeto de relatorio...
                        xmlRelatorio.getFiltro().put(e3.getAttributeValue("chave"), filtro);
                    }
                }
            }

        } catch (Exception e) {
            LoggerEx.log(e);
        }

        return xmlRelatorio;
    }

    /**
     * Método que vai ler todos os relatorios que estão mapeados no arquivo.     
     * @param nome_funcao 
     * @return retorna uma lista com todos os relatórios que poderão ser impressos.     
     */
    public List<XMLRelatorio> getRelatorios(String grupo) {

        List<File> files = DirPoolingController.getFilesDir("relatorio/XML/", grupo + ".*\\.(xml)");

        List<XMLRelatorio> relatorios = new ArrayList<XMLRelatorio>(0);

        XMLRelatorio xmlRelatorio = null;
        XMLFiltro filtro = null;

        try {

            // Faz um loop nos arquivos que foram lidos da pasta.
            for (File f : files) {

                // SAXBuilder que vai processar o XML.
                SAXBuilder sb = new SAXBuilder();
                // Passa a estrutura do arquivo XML para o documento.
                Document doc = sb.build(f);
                // Recuperamos o element principal.
                Element root = doc.getRootElement();
                // Recuperamos os elementos filhos (children).
                List elements = root.getChildren();

                // Faz um loop obtendo os elementos filhos da tag...
                for (Object i : elements) {
                    // converte o objeto lido em um element...
                    Element e = (Element) i;

                    // faz um outro loop para obter os elementos filhos...
                    for (Object i2 : e.getChildren()) {
                        Element e2 = (Element) i2;
                        
                        // criando uma instancia com os valores lidos...
                        xmlRelatorio = new XMLRelatorio(root.getAttributeValue("versao"), root.getAttributeValue("grupo"),
                                                        root.getAttributeValue("modificadoEm"), root.getAttributeValue("usuario"),
                                                        root.getAttributeValue("empresa"), e.getAttributeValue("nome"),
                                                        e.getAttributeValue("caminho"), e.getAttributeValue("componentes"), e.getAttributeValue("obrigatorios"),
                                                        e.getAttributeValue("campoOrdenacao"),
                                                        e.getAttributeValue("tipoOrdenacao"), f,
                                                        Boolean.valueOf(e.getAttributeValue("padrao")),
                                                        Boolean.valueOf(e2.getAttributeValue("editavel")));

                        for (Object i3 : e2.getChildren()) {
                            Element e3 = (Element) i3;

                            // cria um novo filtro para cada chave...
                            filtro = new XMLFiltro(e3.getAttributeValue("valorComparar"),
                                                   e3.getAttributeValue("chaveValorComparador"),
                                                   e3.getAttributeValue("operador"),
                                                   e3.getAttributeValue("comparador"));

                            // adiciona as propriedades do filtro para o objeto de relatorio...
                            xmlRelatorio.getFiltro().put(e3.getAttributeValue("chave"), filtro);
                        }
                    }
                }
                // por fim adiciona para a lista um novo objeto contendo os valores lidos do XML.
                relatorios.add(xmlRelatorio);
            }
        } catch (Exception e) {
            LoggerEx.log(e);
        }
        return relatorios;
    }
}