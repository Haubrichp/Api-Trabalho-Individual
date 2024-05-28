package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumValidationException;

public enum StatusEnum {

    ENVIADO, ENTREGUE, PENDENTE, EM_ROTA, PROCESSANDO;

    @JsonCreator
    public static StatusEnum fromString(String value) throws EnumValidationException {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new EnumValidationException("Status preenchido incorretamente: " + value);
    }
}
