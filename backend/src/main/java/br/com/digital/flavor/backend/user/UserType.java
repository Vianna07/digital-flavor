package br.com.digital.flavor.backend.user;


public enum UserType {
    ADMIN(1),
    MANAGER(2),
    EMPLOYEE(3),
    CUSTOMER(4);

    UserType(int value) {
    }
}
