package ru.maikova.spring.OnlineBank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "communal_service")
@Getter
@Setter
@NoArgsConstructor
public class CommunalService {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sum")
    @Min(value = 1, message = "Сумма должна быть больше 1")
    private double sum;

    @Column(name = "company")
    private String company;

    @Column(name = "personal_account")
    @NotEmpty
    private String personalAccount;

    @Column(name = "doc_number")
    @NotEmpty
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
        return "CommunalService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sum=" + sum +
                ", company='" + company + '\'' +
                ", personalAccount='" + personalAccount + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
