package za.co.study.casetshidiso.demoing;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import za.co.study.casetshidiso.demoing.model.entity.Product;
import za.co.study.casetshidiso.demoing.repository.ProductRepository;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/products")
public class ProductResource {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    private ProductRepository productRepository;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") Long id) {
        logger.info("Get Product by id: " + id);
        return productRepository.getProductById(id);
    }

    @GET
//    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts(@QueryParam("name") String name) {
        if (name == null || name.isEmpty()) {
            logger.info("Get no Products");
//            return new ArrayList<>();
        }
        logger.info("Get all products");
        return productRepository.getAllProducts();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product createProduct(Product product) {
        logger.info("Create product: " + product.getName());
        try{
            return productRepository.createProduct(product);
        }catch(PersistenceException pe){
            logger.info("Error creating product: " + product.getName());
            logger.info("Error message " + pe.getMessage());
            throw new WebApplicationException(Response.Status.BAD_GATEWAY);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product updateProduct(Product product) {
        logger.info("Update product: " + product.getName());
        try{
            return productRepository.updateProduct(product);
        }catch(Exception pe){
            logger.info("Error updating product " + product.getName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteProductById(@PathParam("id") Long id) {
        logger.info("Delete product by id: " + id);
        try {
            productRepository.deleteProduct(id);
        }catch (PersistenceException pe){
            logger.info("Error deleting product: " + id);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}