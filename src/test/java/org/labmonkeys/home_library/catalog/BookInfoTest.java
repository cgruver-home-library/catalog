package org.labmonkeys.home_library.catalog;

import org.junit.jupiter.api.Test;
import org.labmonkeys.home_library.catalog.model.BookInfo;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookInfoTest {

    @Test
    public void testBookInfo() {
        PanacheMock.mock(BookInfo.class);

        
    }
    
}
