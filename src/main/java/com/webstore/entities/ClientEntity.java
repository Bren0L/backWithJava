package com.webstore.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "lojavirtual")
public class ClientEntity implements Serializable {
    public ClientEntity(long registration, String clientAddress, String clientCpf, String clientEmail, String clientName, String clientPassword, String cadDate, PaymentEntity payment) {
        this.registration = registration;
        this.clientAddress = clientAddress;
        this.clientCpf = clientCpf;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
        this.clientPassword = clientPassword;
        this.cadDate = cadDate;
        this.payment = payment;
    }
    public ClientEntity(){}

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "registration")
    private long registration;
    @Basic
    @Column(name = "client_address")
    private String clientAddress;
    @Basic
    @Column(name = "client_cpf")
    private String clientCpf;
    @Basic
    @Column(name = "client_email")
    private String clientEmail;
    @Basic
    @Column(name = "client_name")
    private String clientName;
    @Basic
    @Column(name = "client_password")
    private String clientPassword;
    @Basic
    @Column(name = "cad_date")
    private String cadDate;
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PaymentEntity payment;

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public long getRegistration() {
        return registration;
    }

    public void setRegistration(long registration) {
        this.registration = registration;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getCadDate() {
        return cadDate;
    }

    public void setCadDate(String cadDate) {
        this.cadDate = cadDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity client = (ClientEntity) o;

        if (!Objects.equals(clientAddress, client.clientAddress)) return false;
        if (!Objects.equals(clientCpf, client.clientCpf)) return false;
        if (!Objects.equals(clientEmail, client.clientEmail)) return false;
        if (!Objects.equals(clientName, client.clientName)) return false;
        return (!Objects.equals(cadDate, client.cadDate));
    }

    @Override
    public int hashCode() {
        int result = (int) (registration ^ (registration >>> 32));
        result = 31 * result + (clientAddress != null ? clientAddress.hashCode() : 0);
        result = 31 * result + (clientCpf != null ? clientCpf.hashCode() : 0);
        result = 31 * result + (clientEmail != null ? clientEmail.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (clientPassword != null ? clientPassword.hashCode() : 0);
        result = 31 * result + (cadDate != null ? cadDate.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "registration=" + registration +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientCpf='" + clientCpf + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientPassword='" + clientPassword + '\'' +
                ", cadDate='" + cadDate + '\'' +
                ", payment=" + payment +
                '}';
    }
}
