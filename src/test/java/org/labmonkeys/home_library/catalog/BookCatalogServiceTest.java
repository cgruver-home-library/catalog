package org.labmonkeys.home_library.catalog;

import javax.inject.Inject;

import org.labmonkeys.home_library.catalog.api.BookCatalogService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(OpenLibraryMock.class)
public class BookCatalogServiceTest {

    @Inject
    private BookCatalogService bookCatalogService;

    public void testBookCatalogServiceOL() {
        this.bookCatalogService.getBookInfo("9780062225740");
    }
    
}
