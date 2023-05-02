package ru.maikova.spring.OnlineBank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Card")
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "type")
    @NotEmpty
    private String type;

    @Column(name = "sum")
    @NotEmpty
    @Min(value = 0, message = "Сумма должна быть больше 0")
    private double sum;

    @Column(name = "percent")
    @NotEmpty
    private double percent;

    @Column(name = "number")
    @NotEmpty
    private String number;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sum=" + sum +
                ", percent=" + percent +
                ", number='" + number + '\'' +
                '}';
    }
}
