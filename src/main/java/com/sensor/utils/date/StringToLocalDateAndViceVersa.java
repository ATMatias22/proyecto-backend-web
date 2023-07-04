package com.sensor.utils.date;

import com.sensor.utils.date.constants.DateConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class StringToLocalDateAndViceVersa {
    private static final Logger logger = LoggerFactory.getLogger(StringToLocalDateAndViceVersa.class);

    public Optional<LocalDate> toLocalDate(String strDate, boolean canNull) throws NullPointerException, DateTimeParseException {
        Optional<LocalDate> optionalLocalDate = Optional.empty();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateConstants.FORMAT_DATE);
        try {
            LocalDate localDate = LocalDate.parse(strDate, formatter);
            optionalLocalDate = Optional.of(localDate);
        } catch (DateTimeParseException e) {
            logger.error(String.format("DateTimeParseException: %s",  e.getMessage()));
            throw new RuntimeException("Cadena no cumple con el formato necesario");
        } catch (NullPointerException ne){
            logger.error(String.format("NullPointerException: %s", ne.getMessage()));
            if(!canNull){
                throw new NullPointerException("No puede ser nulo");
            }
        }
        return optionalLocalDate;
    }


    public LocalDate getLocalDate(String date) throws NullPointerException, DateTimeParseException {
        //se creó este AtomicRefence para que pueda editar esta variable dentro de la lambda optionalDate.ifPresent
        AtomicReference<LocalDate> localDate = new AtomicReference<>();
        Optional<LocalDate> optionalDate = this.toLocalDate(date, false);
        //en caso de que exista un Calendar en optionalDate entonces me setea el valor del Calendar dentro del optional
        //adentro del AtomicReference gracias a que se puede pasar un consumer podemos usar esta sintaxis
        optionalDate.ifPresent(localDate::set);
        return localDate.get();
    }

    public LocalDate getLocalDateThatCanBeNull(String date) throws DateTimeParseException{
        AtomicReference<LocalDate> localDate = new AtomicReference<>();
        if (date != null) {
            Optional<LocalDate> optionalDate = this.toLocalDate(date,true);
            optionalDate.ifPresent(localDate::set);
        }
        return localDate.get();
    }


    public String getString(LocalDate ld){
        if(ld == null){
            return null;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(DateConstants.FORMAT_DATE);
        return ld.format(formato);
    }


}
