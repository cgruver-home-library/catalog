package org.labmonkeys.home_library.catalog.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "book_info", indexes = {@Index(name = "idx_title", columnList = "title")} )
public class BookInfo extends PanacheEntityBase {

    @Id()
    @Column(name = "catalog_id")
    private String catalogId;

    @Column()
    private String title;

    @Column()
    private String openLibraryUrl;

    @Column()
    private Long numberOfPages;

    @Column()
    private String coverImageUrl;

    @Column()
    private String publishDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookInfo", cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    private List<Author> authors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookInfo", cascade = CascadeType.ALL)
    @OrderBy("isbn ASC")
    private List<ISBN> isbns;
}
