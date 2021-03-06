package org.labmonkeys.home_library.catalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "book_isbns")
public class ISBN extends PanacheEntityBase {
    
    @Id
    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "book_info_id", nullable = false)
    private BookInfo bookInfo;

    public static BookInfo getBookInfoByIsbn(String isbn){
        BookInfo book = null;
        ISBN entity = find("isbn", isbn).firstResult();
        if (entity != null)
        {
            book = entity.getBookInfo();
        }
        return book;
    }
}
