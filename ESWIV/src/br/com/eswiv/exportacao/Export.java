package br.com.eswiv.exportacao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Renato
 */
public class Export {

    String DIR_FILE;

    public Export() {

    }

    public Export(File diretorio) {
        DIR_FILE = diretorio.getAbsolutePath();
    }

    private static void writeUtf8ToFile(File file, boolean append, String data) throws IOException {
        final byte[] utf8_bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        boolean exists = file.isFile();
        OutputStream out = new FileOutputStream(file, append);
        try {
            if (!exists) {
                // then this is a new file
                // write the BOM marker
                out.write(utf8_bom);
            }
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            try {
                writer.write(data);
            } finally {
                writer.close();
            }
        } finally {
            out.close();
        }
    }

    public Export(String DIR_FILE) {
        this.DIR_FILE = DIR_FILE;
//        try {
            File file = new File(DIR_FILE);
            file.delete();
        try {
            writeUtf8ToFile(new File(DIR_FILE), true, "");
//        } 
        } catch (IOException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gravar(String texto) {

        String conteudo = texto;
        try {
            Writer out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(DIR_FILE, true), "UTF8"));//"ISO-8859-1"
            out.write(conteudo);
            out.close();

            // Tornando o arquivo somente leitura
//            File arquivo = new File(DIR_FILE);
//            arquivo.setReadOnly();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção",
                                          JOptionPane.WARNING_MESSAGE);
        }
    }
}
