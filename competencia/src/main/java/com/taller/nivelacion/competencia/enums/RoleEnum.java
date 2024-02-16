package com.taller.nivelacion.competencia.enums;

public enum RoleEnum {

    ROLE_USER("REGULAR", 2L), ROLE_ADMIN("ADMIN", 1L);

    private String roleName;
    private Long id;

    RoleEnum(String roleName, Long id) {
        this.roleName = roleName;
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Long getId() {
        return id;
    }
}
