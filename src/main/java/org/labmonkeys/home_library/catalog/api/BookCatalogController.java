package org.labmonkeys.home_library.catalog.api;

import javax.inject.Inject;

import org.labmonkeys.home_library.catalog.rest.BookCatalogException;
import org.labmonkeys.home_library.catalog.rest.api.BookCatalogApi;
import org.labmonkeys.home_library.catalog.rest.dto.BookInfoDTO;
import org.labmonkeys.home_library.catalog.service.BookCatalogService;

public class BookCatalogController implements BookCatalogApi {

    @Inject
    private BookCatalogService bookCatalogService;
    @Override
    public void saveBookInfo(BookInfoDTO bookInfo) throws BookCatalogException {
        bookCatalogService.saveBookInfo(bookInfo);
    }

    @Override
    public BookInfoDTO getBookInfo(String isbn) throws BookCatalogException {
        return bookCatalogService.getBookInfoByIsbn(isbn);
    }
    
}