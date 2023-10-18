package autopark.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CLIENTS")
public class Clients implements WithId{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
    @Column(name = "ID")
    private int id;
    @Column(name = "LAST_NAME")
    private String lastName; //SimpleStringProperty
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Column(name = "PASSPORT_SERIA")
    private String passportSeria;
    @Column(name = "PASSPORT_NUM")
    private String passportNum;
    public Clients() {}

    public Clients(int id, String lastName, String firstName, String fatherName, String passportSeria, String passportNum) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fatherName = fatherName;
        this.passportSeria = passportSeria;
        this.passportNum = passportNum;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String father_name) {
        this.fatherName = father_name;
    }

    public String getPassportSeria() {
        return passportSeria;
    }
    public void setPassportSeria(String passport_seria) {
        this.passportSeria = passport_seria;
    }

    public String getPassportNum() {
        return passportNum;
    }
    public void setPassportNum(String passport_num) {
        this.passportNum = passport_num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients client = (Clients) o;
        return id == client.id &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(fatherName, client.fatherName) &&
                Objects.equals(passportSeria, client.passportSeria) &&
                Objects.equals(passportNum, client.passportNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, fatherName, passportSeria, passportNum);
    }

    @Override
    public String toString() {
        return "id:" + id +
                ", " + lastName +
                " " + firstName +
                " " + fatherName;
    }
}
