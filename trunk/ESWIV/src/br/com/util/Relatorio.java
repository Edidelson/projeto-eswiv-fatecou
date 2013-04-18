package br.com.util;

import br.com.eswiv.dao.DAOGenerico;
import br.com.eswiv.exceptions.ReportException;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * DOCUMENTAÇÃO DA CLASSE
 * <br><br>
 * FINALIDADE:
 * <br>
 *
 * <br><br>
 * HISTÓRICO DE DESENVOLVIMENTO:
 * <br>
 * 25/03/2011 - @author Everton - Primeira versão
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

public class Relatorio {

    /**
     * Carrega o relatório informado utilizando a lista e parâmetros passados
     * @param nome - String - Nome do arquivo .jasper do relatório
     * @param lista - List - lista de objetos para o preenchimento do relatório
     * @param parametros - Map - map de parâmetros para o relatório informado
     * @throws ReportException - Caso houver algum problema e o relatório não possa ser gerado
     */
    public void gerarRelatorio(String nome, List lista, Map parametros) throws ReportException {
        try {
            File arq = new File("relatorio/" + nome + ".jasper"); 

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arq);
            JRDataSource jrds = new JRBeanCollectionDataSource(lista);
            
            DAOGenerico.abrirSessao();
            JasperPrint jprint = JasperFillManager.fillReport(jasperReport, parametros,jrds);
            br.com.util.JasperViewer.viewReport(jprint, false, new Locale("pt", "BR"));
        } catch(JRException ex) {
            ex.printStackTrace();
            Util.logException(ex);
            throw new ReportException();
        }
    }
    
    public void gerarRelatorio(String nome, Map parametros) throws ReportException {
        try {
            File arq = new File("relatorio/" + nome + ".jasper");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arq);
            
            DAOGenerico.abrirSessao();            
            
            JasperPrint jprint = JasperFillManager.fillReport(jasperReport, parametros, DAOGenerico.getSession().connection());
            br.com.util.JasperViewer.viewReport(jprint, false, new Locale("pt", "BR"));
        } catch(JRException ex) {
            ex.printStackTrace();
            Util.logException(ex);
            throw new ReportException();
        }
    }
    
    public void gerarRelatorio(XMLRelatorio relatorio, Map parametros) throws ReportException {
        try {
            //URL arq = getClass().getResource("/br/com/sgt/relatorios/modelos/" + nome + ".jasper");
            //URL arq = getClass().getResource("relatorios/" + nome + ".jasper");
            File arq = new File(relatorio.getCaminho() + ".jasper");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arq);
            
            //JRDataSource jrds = new JRBeanCollectionDataSource(lista);
            DAOGenerico.abrirSessao();
            JasperPrint jprint = JasperFillManager.fillReport(jasperReport, parametros, DAOGenerico.getSession().connection());
            br.com.util.JasperViewer.viewReport(jprint, false, new Locale("pt", "BR"));
        } catch(JRException ex) {
            ex.printStackTrace();
            Util.logException(ex);
            throw new ReportException();
        }
    }
}
