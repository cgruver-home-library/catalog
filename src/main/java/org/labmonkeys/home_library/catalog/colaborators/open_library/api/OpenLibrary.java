package org.labmonkeys.home_library.catalog.colaborators.open_library.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.labmonkeys.home_library.catalog.colaborators.open_library.dto.BookInfoOL;

@Path("/api")
@RegisterRestClient(configKey = "open_library_api")
@ApplicationScoped
public interface OpenLibrary {
    
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public BookInfoOL getBookInfo(@QueryParam("bibkeys") final String isbn, @QueryParam("format") final String format, @QueryParam("jscmd") final String jscmd);
}
