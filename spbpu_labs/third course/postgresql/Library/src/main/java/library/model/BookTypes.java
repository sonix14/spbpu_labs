package autopark.model;

import javax.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "BOOK_TYPES")
public class BookTypes implements WithId{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CNT")
    private int cnt;
    @Column(name = "FINE")
    private BigDecimal fine;
    @Column(name = "DAY_COUNT")
    private int dayCount;

    public BookTypes() {}

    public BookTypes(int id, String name, int cnt, BigDecimal fine, int dayCount) {
        this.id = id;
        this.name = name;
        this.cnt = cnt;
        this.fine = fine;
        this.dayCount = dayCount;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCnt() {
        return cnt;
    }
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public BigDecimal getFine() {
        return fine;
    }
    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public int getDayCount() {
        return dayCount;
    }
    public void setDayCount(int day_count) {
        this.dayCount = day_count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookTypes type = (BookTypes) o;
        return id == type.id &&
                Objects.equals(name, type.name) &&
                Objects.equals(cnt, type.cnt) &&
                Objects.equals(fine, type.fine) &&
                Objects.equals(dayCount, type.dayCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnt, fine, dayCount);
    }

    @Override
    public String toString() {
        return  "id:" + id +
                " " + name;
    }
}
