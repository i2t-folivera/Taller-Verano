package com.nivelacion.taller.exceptions;

public class ModelNotFoundException extends Exception {
    private final static String MODEL_DOESNT_EXIST = "id %s in model %s does not exist";

    public ModelNotFoundException(Long id, String modelName) {
        super(String.format(MODEL_DOESNT_EXIST, id, modelName));
    }

    public ModelNotFoundException() {
        super();
    }
}
