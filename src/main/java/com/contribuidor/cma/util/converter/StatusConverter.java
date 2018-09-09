package com.contribuidor.cma.util.converter;

import com.contribuidor.cma.entities.enumeration.Status;
import org.springframework.core.convert.converter.Converter;

public class StatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String s) {
        return Status.fromValue(s);
    }
}
