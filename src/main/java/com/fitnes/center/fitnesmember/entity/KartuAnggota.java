package com.fitnes.center.fitnesmember.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "KARTU_ANGGOTA")
public class KartuAnggota {
    String noKartu;
    String cvv;
    Date expiredDate;
    String nama;
    @Id
    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }
    @Column(name = "CVV")
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    @Column(name = "EXPIRED_DATE")
    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
    @Column(name = "NAMA")
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "KartuAnggota{" +
                "noKartu='" + noKartu + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expiredDate=" + expiredDate +
                ", nama='" + nama + '\'' +
                '}';
    }
}
