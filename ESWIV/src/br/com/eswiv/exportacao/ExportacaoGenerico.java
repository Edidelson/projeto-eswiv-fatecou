/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.exportacao;

import br.com.eswiv.auxiliares.genericos.IExportavel;
import java.io.File;
import java.text.NumberFormat;
import java.util.List;

/**
 *
 * @author Everton
 */
public abstract class ExportacaoGenerico {
   
    /**
     * Diretório padrão para os arquivos da exportação de Calculos 
     */
    public static final File DIR_CALC = new File("exportacao\\calculos\\parcelas");
    /**
     * Diretorio padrçao para os arquivos da exportação de Socios
     */
    public static final File DIR_SOCIO = new File("exportacao\\socio");
    /**
     * Cria os diretórios padrões para exportação
     */
    static {
        DIR_CALC.mkdirs();
        DIR_SOCIO.mkdirs();
    }
    
    /**
     * Exporta um registro para o Radar
     * @param diretorio - File diretório onde o arquivo será gerado
     * @param registro - IExportavel objeto a ser exportado
     */
    abstract public void gerarExportacao(File diretorio, IExportavel registro);
    
    /**
     * Exporta uma lista de registros para o Radar
     * @param diretorio - File diretório onde o arquivo será gerado
     * @param registros - List<IExportavel> lista de registros que serão exportados
     */
    abstract public <T extends IExportavel> void gerarExportacao(File diretorio, List<T> registros);
    
    /**
     * Grava a String no arquivo enviado
     * @param arquivo - File arquivo a ser utilizado para a gravação
     * @param registro - String com o texto a ser gravado
     */
    protected void gerarArquivo(File arquivo, String registro) {
        Export export = new Export(arquivo.getAbsolutePath());
        export.gravar(registro.toString());
    }
    
    /**
     * Formata um código o deixando com o número de caracteres informados
     * @param codigo - int código a ser formatado
     * @param numCaracteres - int número de caracteres que o resultado deve possuir
     * @return String código com o número de caracteres informados, preenchidos
     * com zero.
     */
    protected String formatarCodigo(int codigo, int numCaracteres) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(0);
        nf.setMinimumFractionDigits(0);
        nf.setMaximumIntegerDigits(numCaracteres);
        nf.setMinimumIntegerDigits(numCaracteres);
        String cod = nf.format(codigo);
        cod = cod.replace(".", "");
        cod = cod.replace(",", "");
        return cod;
    }
}
