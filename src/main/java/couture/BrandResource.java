package couture;
import couture.Brand;
import couture.BrandService;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import io.dropwizard.auth.Auth;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class BrandResource {
	private final int defaultSize=5;
	private final BrandService brandService;
 
    public BrandResource(BrandService bs) {
        this.brandService = bs;
    }

    

 	// @PermitAll
    @GET
    public Response getBrands(@QueryParam("size") Optional<Integer> size) {
        return Response.ok(brandService.getBrands()).build();
    }

    // @RolesAllowed({ "ADMIN" })
    @POST
    @Path("/{id}/{name}")
    public Response createBrand(@PathParam("id") Integer id, @PathParam("name") String name) {
        Brand brandC = new Brand(id,name);
        return Response.ok(brandService.createBrand(id,name)).build();
    }

    // @RolesAllowed({ "ADMIN" })
    @GET
    @Path("/{id}")
    public Response getBrand(@PathParam("id") Integer id) {
        return Response.ok(brandService.getBrand(id)).build();
    }


    // @RolesAllowed({ "ADMIN" })
    @DELETE
    @Path("/delete/{id}")
    public Response  deleteBrand(@PathParam("id") Integer id) {
        Map<String,String> response= new HashMap<>();
        response.put("status",brandService.deleteBrand(id));
        return Response.ok(response).build(); 
    }
}
 