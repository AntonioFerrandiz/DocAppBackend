package com.afb.DocApp.shared.exception;

import java.time.LocalDateTime;

public class ResourceDateException extends RuntimeException{
    public ResourceDateException(String entity, LocalDateTime date){
        super(String.format("El horario de la cita no puede ser el mismo para el inicio y fin",entity,date));
    }
    public  ResourceDateException(LocalDateTime startDate, LocalDateTime endDate){
        super(String.format("El horario de la cita no puede ser agendado antes de la fecha actual",startDate,endDate));
    }
    public  ResourceDateException(LocalDateTime endDate){
        super(String.format("El horario de finalización de la cita no puede ser agendado antes de la fecha y hora de inicio",endDate));
    }
}
