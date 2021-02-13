package org.labmonkeys.home_library.catalog.dto;

import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class BookInfoDTO {
    
    String catalogId;
    String title;
    String openLibraryUrl;
    Long numberOfPages;
    String coverImageUrl;
    String publishDate;
    boolean inCatalog;
    List<String> isbns;
    List<AuthorDTO> authors = null;
}