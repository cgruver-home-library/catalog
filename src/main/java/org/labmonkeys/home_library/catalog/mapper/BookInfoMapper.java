package org.labmonkeys.home_library.catalog.mapper;

import org.labmonkeys.home_library.catalog.model.Author;
import org.labmonkeys.home_library.catalog.model.BookInfo;
import org.labmonkeys.home_library.catalog.rest.dto.AuthorDTO;
import org.labmonkeys.home_library.catalog.rest.dto.BookInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface BookInfoMapper {

    AuthorDTO authorEntityToDto(Author entity);

    Author authorDtoToEntity(AuthorDTO dto);

    BookInfoDTO bookInfoEntityToDto(BookInfo entity);

    BookInfo bookInfoDtoToEntity(BookInfoDTO dto);
}