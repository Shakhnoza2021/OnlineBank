package ru.maikova.spring.OnlineBank.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Phone_communication")
@Getter
@Setter
@NoArgsConstructor
public class PhoneCommunication {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "provider")
    private String provider;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "sum")
    private double sum;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Override
    public String toString() {
        return "PhoneCommunication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provider='" + provider + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sum=" + sum +
                ", date=" + date +
                '}';
    }
}
