package com.fitnes.center.fitnesmember.entity;
import javax.persistence.*;

@Entity
@Table(name = "ANGGOTA")
public class Anggota {
    String nama;
    String email;
    String noHp;
    String pass;
    KartuAnggota kartuAnggota;
    @ManyToOne
    @JoinColumn(name = "NO_KARTU")
    public KartuAnggota getKartuAnggota() {
        return kartuAnggota;
    }

    public void setKartuAnggota(KartuAnggota kartuAnggota) {
        this.kartuAnggota = kartuAnggota;
    }

    @Column(name = "NAMA")
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Id
    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
    @Column(name = "PASSWORD")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Anggota{" +
                "nama='" + nama + '\'' +
                ", email='" + email + '\'' +
                ", noHp='" + noHp + '\'' +
                ", pass='" + pass + '\'' +
                ", kartuAnggota=" + kartuAnggota +
                '}';
    }
}
