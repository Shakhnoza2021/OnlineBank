package ru.maikova.spring.OnlineBank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "surname")
    @NotEmpty
    @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
    private String surname;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    private String name;

    @Column(name = "middle_name")
    @NotEmpty
    @Size(min = 2, max = 50, message = "Отчество должно быть от 2 до 50 символов")
    private String middleName;

    @Column(name = "date_of_birth")
    @NotEmpty
    private String dateOfBirth;

    @Column(name = "phone_number")
    @NotEmpty
    @Pattern(regexp = "8\\d{10}", message = "Номер телефона должен начинаться с 8 и состоять из 11 цифр")
    private String phoneNumber;

    @Column(name = "email")
    @NotEmpty
    @Email(message = "Адрес электронной почты должен быть валидным")
    private String email;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Card> cards;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Credit> credits;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<CommunalService> communalServices;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Fine> fines;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<PhoneCommunication> phoneCommunications;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Transfer> transfers;

    public String getFullName() {
        return name + " " + middleName + " " + surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
