package autopark.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BOOKS")
public class Books implements WithId{

    @Id
    //@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CNT")
    private int cnt;
    @ManyToOne()
    @JoinColumn(name = "TYPE_ID")
    private BookTypes type;

    public Books() {}

    public Books(int id, String name, int cnt, BookTypes type) {
        this.id = id;
        this.name = name;
        this.cnt = cnt;
        this.type = type;
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

    public BookTypes getType() {
        return type;
    }
    public void setType(BookTypes type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books book = (Books) o;
        return id == book.id &&
                Objects.equals(name, book.name) &&
                Objects.equals(cnt, book.cnt) &&
                Objects.equals(type, book.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnt, type);
    }

    @Override
    public String toString() {
        return "id:" + id +
                " " + name;
    }
}
