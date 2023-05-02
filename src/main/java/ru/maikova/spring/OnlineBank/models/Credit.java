package ru.maikova.spring.OnlineBank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Credit")
@Getter
@Setter
@NoArgsConstructor
public class Credit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "term")
    private int term;

    @Column(name = "sum")
    @NotEmpty
    @Min(value = 0, message = "Сумма должна быть больше 0")
    private double sum;

    @Column(name = "percent")
    @NotEmpty
    private double percent;

    @Column(name = "monthly_payment")
    private double monthlyPayment;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @Column(name = "last_payment")
    private Date lastPayment;

    @Column(name = "total_payed")
    private double totalPayed;

    @Column(name = "remains_to_pay")
    private double remainsToPay;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", term=" + term +
                ", sum=" + sum +
                ", percent=" + percent +
                ", monthlyPayment=" + monthlyPayment +
                ", dateOfIssue=" + dateOfIssue +
                ", lastPayment=" + lastPayment +
                ", totalPayed=" + totalPayed +
                ", remainsToPay=" + remainsToPay +
                '}';
    }
}
