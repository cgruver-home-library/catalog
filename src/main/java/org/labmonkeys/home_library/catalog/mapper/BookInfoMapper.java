package org.labmonkeys.home_library.catalog.mapper;

import java.util.ArrayList;
import java.util.List;

import org.labmonkeys.home_library.catalog.colaborators.open_library.dto.AuthorOL;
import org.labmonkeys.home_library.catalog.colaborators.open_library.dto.BookInfoDetailOL;
import org.labmonkeys.home_library.catalog.colaborators.open_library.dto.BookInfoOL;
import org.labmonkeys.home_library.catalog.dto.AuthorDTO;
import org.labmonkeys.home_library.catalog.dto.BookInfoDTO;
import org.labmonkeys.home_library.catalog.model.Author;
import org.labmonkeys.home_library.catalog.model.BookInfo;
import org.labmonkeys.home_library.catalog.model.ISBN;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface BookInfoMapper {

    AuthorDTO authorEntityToDto(Author entity);

    List<AuthorDTO> authorsEntityToDtos(List<Author> entities);

    @Mapping(target = "bookInfo", ignore = true)
    @Mapping(target = "id", ignore = true)
    Author authorDtoToEntity(AuthorDTO dto);

    List<Author> authorDtosToEntitys(List<AuthorDTO> dtos);

    @Mapping(target = "isbns", ignore = true)
    BookInfoDTO bookInfoEntityToDto(BookInfo entity);

    @AfterMapping
    default void bookInfoEntityToDtoCustom(BookInfo entity, @MappingTarget BookInfoDTO dto) {
        List<String> isbns = new ArrayList<>();
        for (ISBN isbn : entity.getIsbns()) {
            isbns.add(isbn.getIsbn());
        }
        dto.setIsbns(isbns);
    }

    @Mapping(target = "isbns", ignore = true)
    BookInfo bookInfoDtoToEntity(BookInfoDTO dto);

    @AfterMapping
    default void bookInfoDtoToEntityCustom(BookInfoDTO dto, @MappingTarget BookInfo bookInfo) {
        List<ISBN> isbns = new ArrayList<ISBN>();
        for (String isbn : dto.getIsbns()) {
            ISBN entity = new ISBN();
            entity.setBookInfo(bookInfo);
            entity.setIsbn(isbn);
            isbns.add(entity);
        }
        bookInfo.setIsbns(isbns);
        for (Author author : bookInfo.getAuthors()) {
            author.setBookInfo(bookInfo);
        }
    }

    @Mapping(target = "openLibraryUrl", source = "url")
    @Mapping(target = "name", source = "name")
    AuthorDTO authorOlToAuthorDTO(AuthorOL author);

    List<AuthorDTO> authorsOlToAuthorDTOs(List<AuthorOL> authors);

    @Mapping(source = "details.title", target = "title")
    @Mapping(source = "details.url", target = "openLibraryUrl")
    @Mapping(source = "details.numberOfPages", target = "numberOfPages")
    @Mapping(source = "details.cover.small", target = "coverImageUrl")
    @Mapping(source = "details.publishDate", target = "publishDate")
    @Mapping(source = "details.authors", target = "authors")
    @Mapping(target = "inCatalog", ignore = true)
    @Mapping(target = "catalogId", ignore = true)
    @Mapping(target = "isbns", ignore = true)
    BookInfoDTO bookInfoOlToBookInfoDTO(BookInfoOL bookInfo);

    @AfterMapping
    default void bookInfoOlToBookInfoDtoCustom(BookInfoOL bookInfo, @MappingTarget BookInfoDTO book) {
        BookInfoDetailOL details = bookInfo.getDetails();
        List<String> isbns = new ArrayList<String>();
        List<String> isbn13 = details.getIdentifiers().getIsbn13();
        List<String> isbn10 = details.getIdentifiers().getIsbn10();
        if (isbn13 != null) {
            for (String isbn : isbn13) {
                isbns.add(isbn);
            }
        }
        if (isbn10 != null) {
            for (String isbn : isbn10) {
                isbns.add(isbn);
            }
        }
        book.setIsbns(isbns);
        book.setCatalogId(details.getIdentifiers().getOpenlibrary().get(0));
    }
}
