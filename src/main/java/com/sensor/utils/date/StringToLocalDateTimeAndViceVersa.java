package com.sensor.utils.date;

import com.sensor.utils.date.constants.DateConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class StringToLocalDateTimeAndViceVersa {
    private static final Logger logger = LoggerFactory.getLogger(StringToLocalDateTimeAndViceVersa.class);

    public Optional<LocalDateTime> toLocalDateTime(String strDateTime, boolean canNull) throws NullPointerException, DateTimeParseException {
        Optional<LocalDateTime> optionalLocalDate = Optional.empty();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateConstants.FORMAT_DATE_TIME);
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, formatter);
            optionalLocalDate = Optional.of(localDateTime);
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


    public LocalDateTime getLocalDateTime(String date) throws NullPointerException, DateTimeParseException {
        //se creó este AtomicRefence para que pueda editar esta variable dentro de la lambda optionalDate.ifPresent
        AtomicReference<LocalDateTime> localDateTime = new AtomicReference<>();
        Optional<LocalDateTime> optionalDate = this.toLocalDateTime(date, false);
        //en caso de que exista un Calendar en optionalDate entonces me setea el valor del Calendar dentro del optional
        //adentro del AtomicReference gracias a que se puede pasar un consumer podemos usar esta sintaxis
        optionalDate.ifPresent(localDateTime::set);
        return localDateTime.get();
    }

    public LocalDateTime getLocalDateTimeThatCanBeNull(String date) throws DateTimeParseException{
        AtomicReference<LocalDateTime> localDateTime = new AtomicReference<>();
        if (date != null) {
            Optional<LocalDateTime> optionalDate = this.toLocalDateTime(date,true);
            optionalDate.ifPresent(localDateTime::set);
        }
        return localDateTime.get();
    }


    public String getString(LocalDateTime ldt){
        if(ldt == null){
            return null;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(DateConstants.FORMAT_DATE_TIME);
        return ldt.format(formato);
    }


}
