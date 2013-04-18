/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Edidelson
 */
public class LowerCaseDocument extends PlainDocument{
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {   
  
              if (str == null) {   
                      return;   
              }   
              char[] upper = str.toCharArray();   
              for (int i = 0; i < upper.length; i++) {   
                      upper[i] = Character.toLowerCase(upper[i]); 
              }   
              super.insertString(offs, new String(upper), a);   
          }   
}
