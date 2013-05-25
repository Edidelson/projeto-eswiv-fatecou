/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eswiv.auxiliares;

import br.com.eswiv.exceptions.CalculoException;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Edidelson
 */
public interface ICalculo {

    void calcular();

    Double calcular8Hrs() throws CalculoException;

    Double calcular16Hrs() throws CalculoException;

    Double calcular24Hrs() throws CalculoException;

    Integer getMeses(Date data, DateTime now);

    Integer getAnos(Date data, DateTime now);
}
