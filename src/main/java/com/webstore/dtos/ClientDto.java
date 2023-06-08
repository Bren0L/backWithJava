package com.webstore.dtos;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ClientDto {
    @Email
    @NotBlank
    private String clientEmail;
    @NotBlank
    private String clientCpf;
    @NotBlank
    private String clientPassword;
    @Nullable
    private String clientAddress;
    @NotBlank
    private String clientName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Nullable
    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(@Nullable String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
