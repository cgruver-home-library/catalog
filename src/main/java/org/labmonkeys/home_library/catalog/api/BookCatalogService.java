package org.labmonkeys.home_library.catalog.api;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.labmonkeys.home_library.catalog.BookCatalogException;
import org.labmonkeys.home_library.catalog.aop.Audited;
import org.labmonkeys.home_library.catalog.colaborators.open_library.api.OpenLibrary;
import org.labmonkeys.home_library.catalog.dto.BookInfoDTO;
import org.labmonkeys.home_library.catalog.mapper.BookInfoMapper;
import org.labmonkeys.home_library.catalog.model.BookInfo;
import org.labmonkeys.home_library.catalog.model.ISBN;

@Path("/bookCatalog")
@ApplicationScoped
public class BookCatalogService {

    final Logger LOG = Logger.getLogger(BookCatalogService.class);

    @Inject
    private BookInfoMapper bookInfoMapper;

    @Inject
    @RestClient
    private OpenLibrary openLibrary;

    @GET
    @Path("/getBookInfo/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Audited
    public Response getBookInfo(@PathParam("isbn") String isbn) {
        LOG.info("getBookInfoByIsbn method invoked!");
        BookInfoDTO bookInfoDto = null;
        BookInfo bookInfoEntity = null;
        try {
            bookInfoEntity = ISBN.getBookInfoByIsbn(isbn);
            if (bookInfoEntity == null) {
                isbn = "ISBN:" + isbn;
                bookInfoDto = bookInfoMapper.bookInfoOlToBookInfoDTO(openLibrary.getBookInfo(isbn, "json", "data"));
                bookInfoDto.setInCatalog(false);
            } else {
                bookInfoDto = bookInfoMapper.bookInfoEntityToDto(bookInfoEntity);
                bookInfoDto.setInCatalog(true);
            }
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(bookInfoDto).build();
    }

    @POST
    @Path("/saveBookInfo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response saveBookInfo(BookInfoDTO dto) {
        try {
            BookInfo bookInfoEntity = bookInfoMapper.bookInfoDtoToEntity(dto);
            BookInfo.persist(bookInfoEntity);
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/deleteBookInfo/{catalogId}")
    @Transactional
    public Response deleteBookInfo(@PathParam("catalogId") String catalogId) {
        try {
            if (!BookInfo.deleteById(catalogId)) {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }
}
