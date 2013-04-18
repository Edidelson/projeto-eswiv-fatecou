
package br.com.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Transforma o conteúdo do JTextField em maiúsculo
 * @author Edidelson
 */
public class UpperCaseDocument extends PlainDocument {
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {   
  
              if (str == null) {   
                      return;   
              }   
              char[] upper = str.toCharArray();   
              for (int i = 0; i < upper.length; i++) {   
                      upper[i] = Character.toUpperCase(upper[i]); 
              }   
              super.insertString(offs, new String(upper), a);   
          }   
}