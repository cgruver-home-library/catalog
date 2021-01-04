package org.labmonkeys.home_library.catalog.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.labmonkeys.home_library.catalog.aop.Audited;
import org.labmonkeys.home_library.catalog.mapper.BookInfoMapper;
import org.labmonkeys.home_library.catalog.model.BookInfo;
import org.labmonkeys.home_library.catalog.model.ISBN;
import org.labmonkeys.home_library.catalog.rest.BookCatalogException;
import org.labmonkeys.home_library.catalog.rest.dto.BookInfoDTO;
import org.labmonkeys.home_library.openlibrary.rest.api.OpenLibrary;

@ApplicationScoped
public class BookCatalogService {

    @Inject
    private BookInfoMapper bookInfoMapper;

    @Inject
    @RestClient
    private OpenLibrary openLibrary;

    @Audited
    public BookInfoDTO getBookInfoByIsbn(String isbn) throws BookCatalogException {

        final Logger LOG = Logger.getLogger(BookCatalogService.class);
        LOG.info("getBookInfoByIsbn method invoked!");
        BookInfoDTO bookInfoDto = null;
        BookInfo bookInfoEntity = null;
        bookInfoEntity = ISBN.getBookInfoByIsbn(isbn);
        if (bookInfoEntity == null) {
            isbn = "ISBN:" + isbn;
            bookInfoDto = bookInfoMapper.bookInfoOlToBookInfoDTO(openLibrary.getBookInfo(isbn, "json", "data"));
            bookInfoDto.setInCatalog(false);
        } else {
            bookInfoDto = bookInfoMapper.bookInfoEntityToDto(bookInfoEntity);
            bookInfoDto.setInCatalog(true);
        }

        return bookInfoDto;
    }

    @Transactional
    public void saveBookInfo(BookInfoDTO dto) throws BookCatalogException {
        BookInfo bookInfoEntity = bookInfoMapper.bookInfoDtoToEntity(dto);
        BookInfo.persist(bookInfoEntity);
    }
}
