package ua.cargo.entities.enums;

public enum Role {SUPERADMIN(0), ANONYMOUS(1), CUSTOMER(2), MANAGER(3);
    private final Integer value;
    
    Role(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public static Role getRole(Integer value) {
        if (value == null) return ANONYMOUS;
        for (Role role: Role.values()) {
            if (role.value.equals(value)) {
                return role;
            }
        }
        return ANONYMOUS;
    }
}