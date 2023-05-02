package ru.maikova.spring.OnlineBank.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Transfer")
@Getter
@Setter
@NoArgsConstructor
public class Transfer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "card_from")
    private String cardFrom;

    @Column(name = "card_to")
    private String cardTo;

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
        return "Transfer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardFrom='" + cardFrom + '\'' +
                ", cardTo='" + cardTo + '\'' +
                ", sum=" + sum +
                ", date=" + date +
                '}';
    }
}
