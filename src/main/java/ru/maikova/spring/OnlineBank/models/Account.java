package ru.maikova.spring.OnlineBank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Account")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "sum")
    @NotEmpty
    @Min(value = 0, message = "Сумма должна быть больше 0")
    private double sum;

    @Column(name = "number")
    @NotEmpty
    private String number;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> card;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sum=" + sum +
                ", number='" + number + '\'' +
                '}';
    }
}
