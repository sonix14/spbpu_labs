package autopark.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "JOURNAL")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "countBooksStoreProcedure",
                query = "SELECT * FROM countbooksf(:lastName,:firstName,:fatherName)"
        ),
        @NamedNativeQuery(
                name = "clientFineStoreProcedure",
                query = "SELECT * FROM clientfinepr(:lastName,:firstName,:fatherName)"
        ),
        @NamedNativeQuery(
                name = "booksTopStoreProcedure",
                query = "SELECT * FROM bookstoppr()"
        ),
        @NamedNativeQuery(
                name = "maxFineStoreProcedure",
                query = "SELECT * FROM maxfine()"
        )
})
public class Journal implements WithId {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
    @Column(name = "ID")
    private int id;
    @Column(name = "DATE_BEG")
    private LocalDateTime dateBeg;
    @Column(name = "DATE_END")
    private LocalDateTime dateEnd;
    @Column(name = "DATE_RET")
    private LocalDateTime dateRet;
    @ManyToOne()
    @JoinColumn(name = "BOOK_ID")
    private Books book;
    @ManyToOne()
    @JoinColumn(name = "CLIENT_ID")
    private Clients client;

    public Journal() {}

    public Journal(int id, LocalDateTime dateBeg, LocalDateTime dateEnd, LocalDateTime dateRet, Books book, Clients client) {
        this.id = id;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateRet = dateRet;
        this.book = book;
        this.client = client;
    }

    @Override
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateBeg() {
        return dateBeg;
    }
    public void setDateBeg(LocalDateTime date_beg) {
        this.dateBeg = date_beg;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(LocalDateTime date_end) {
        this.dateEnd = date_end;
    }

    public LocalDateTime getDateRet() {
        return dateRet;
    }
    public void setDateRet(LocalDateTime date_ret) {
        this.dateRet = date_ret;
    }

    public Books getBook() {
        return book;
    }
    public void setBook(Books book) {
        this.book = book;
    }

    public Clients getClient() {
        return client;
    }
    public void setClient(Clients client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journal journal = (Journal) o;
        return id == journal.id &&
                Objects.equals(dateBeg, journal.dateBeg) &&
                Objects.equals(dateEnd, journal.dateEnd) &&
                Objects.equals(dateRet, journal.dateRet) &&
                Objects.equals(book, journal.book) &&
                Objects.equals(client, journal.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateBeg, dateEnd, dateRet, book, client);
    }

    @Override
    public String toString() {
        return "Journa: {" +
                "id = " + id +
                ", date_beg = " + dateBeg +
                ", date_end = " + dateEnd +
                ", date_ret = " + dateRet +
                ", book = " + book +
                ", client = " + client +
                '}';
    }
}
