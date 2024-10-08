package za.co.study.casetshidiso.demoing.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.study.casetshidiso.demoing.config.RequiresAuthentication;
import za.co.study.casetshidiso.demoing.domain.model.product.Product;
import za.co.study.casetshidiso.service.ProductService;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

@Path("/products")
public class ProductResource {

    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    private ProductService productService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProduct(@PathParam("id") long id){
        logger.info("Get Product by id: " + id);
        return productService.findProduct(id);
    }

    @GET
//    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllProducts() {
        logger.info("Get all products");
        return productService.findAllProducts();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        logger.info("Create product: " + product.getName());
        return productService.createProduct(product);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product){
        logger.info("Update product: " + product.getName());
        return productService.updateProduct(product);
    }

    @DELETE
    @Path("{id}")
    @RequiresAuthentication
    public Response deleteProductById(@PathParam("id") long id) {
        logger.info("Delete product by id: " + id);
        return productService.deleteProduct(id);
    }
}