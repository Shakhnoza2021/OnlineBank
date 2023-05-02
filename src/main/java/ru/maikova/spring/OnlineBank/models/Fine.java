package ru.maikova.spring.OnlineBank.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Fine")
@Getter
@Setter
@NoArgsConstructor
public class Fine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "sum")
    private double sum;

    @Column(name = "doc_number")
    private String docNumber;

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
        return "Fine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", sum=" + sum +
                ", docNumber='" + docNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
