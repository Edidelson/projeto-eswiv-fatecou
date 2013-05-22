/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.auxiliares;

import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Edidelson
 */
public interface ICalculo {
    
   void calcular();
   Double calcular8Hrs();
   Double calcular16Hrs();
   Double calcular24Hrs();
   Integer getMeses(Date data, DateTime now);
   Integer getAnos(Date data, DateTime now);
}
