package com.warehouse.auth.base.security;

public enum UserPermission {
    PRODUCT_READ("product_read"),
    PRODUCT_WRITE("product_write"),
    BILL_READ("bill_read"),
    BILL_WRITE("bill_write"),
    INVOICE_READ("invoice_read"),
    INVOICE_WRITE("invoice_write"),
    SPACE_READ("space_read"),
    SPACE_WRITE("space_write"),
    STOCK_READ("stock_read"),
    STOCK_WRITE("stock_write"),
    STOCK_TRANSACTION_READ("stock_transaction_read"),
    STOCK_TRANSACTION_WRITE("stock_transaction_write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
