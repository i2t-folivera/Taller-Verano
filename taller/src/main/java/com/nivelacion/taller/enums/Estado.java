package com.nivelacion.taller.enums;

public enum Estado {
    INACTIVO(0),
    ACTIVO(1);

    private final int valor;

    Estado(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static Estado fromValor(int valor) {
        for (Estado estado : Estado.values()) {
            if (estado.getValor() == valor) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Valor de estado inválido: " + valor);
    }
}
