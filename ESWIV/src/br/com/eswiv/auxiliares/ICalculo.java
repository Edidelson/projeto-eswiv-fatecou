/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.auxiliares;

import java.util.Date;

/**
 *
 * @author Edidelson
 */
public interface ICalculo {
    
   void calcular();
   Double calcular8Hrs();
   Double calcular16Hrs();
   Double calcular24Hrs();
   Integer getMeses(Date data);
   Integer getAnos(Date data);
}
