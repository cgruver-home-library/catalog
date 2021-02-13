package org.labmonkeys.home_library.catalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "book_isbns", uniqueConstraints = {@UniqueConstraint(columnNames = {"isbn"}, name = "isbn")}, indexes = { @Index(name = "idx_isbn", columnList = "isbn")} )
public class ISBN extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

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
