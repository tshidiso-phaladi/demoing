package za.co.study.casetshidiso.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.study.casetshidiso.demoing.domain.model.ErrorResponse;
import za.co.study.casetshidiso.demoing.domain.model.product.Product;
import za.co.study.casetshidiso.demoing.domain.model.product.ProductRepository;
import za.co.study.casetshidiso.demoing.exceptions.ProductNotFoundException;
import za.co.study.casetshidiso.demoing.exceptions.ZeroPriceException;
import za.co.study.casetshidiso.service.ProductService;

import java.util.List;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    private static final String ERROR_CODE_PRODUCT_NOT_FOUND = "9999";
    private static final String ERROR_MESSAGE_PRODUCT_NOT_FOUND = "Product not found";
    private static final String ERROR_CODE_ZERO_PRICE_ON_PRODUCT = "9998";
    private static final String ERROR_MESSAGE_ZERO_PRICE_ON_PRODUCT = "Product price may not be Zero (0)";

    @Inject
    private ProductRepository productRepository;

    @Override
    public Response findAllProducts() {
        List<Product> allProducts = productRepository.findAllProducts();
        return Response.ok(allProducts).build();
    }

    @Override
    public Response findProduct(long id) {
        Product product;
        try {
            product = productRepository.findProduct(id);
        } catch (ProductNotFoundException e) {
            ErrorResponse errorResponse =
                    new ErrorResponse(ERROR_CODE_PRODUCT_NOT_FOUND, ERROR_MESSAGE_PRODUCT_NOT_FOUND);

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }
        return Response.ok(product).build();
    }

    @Override
    public Response createProduct(Product product){
        Product createdProduct;
        try {
            createdProduct = productRepository.createProduct(product);
        } catch (ZeroPriceException e) {
            ErrorResponse errorResponse =
                    new ErrorResponse(ERROR_CODE_ZERO_PRICE_ON_PRODUCT, ERROR_MESSAGE_ZERO_PRICE_ON_PRODUCT);

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.ok(createdProduct).build();
    }

    @Override
    public Response updateProduct(Product product) {
        Product updatedProduct;
        try {
            updatedProduct = productRepository.updateProduct(product);
        } catch (ProductNotFoundException e) {
            ErrorResponse errorResponse =
                    new ErrorResponse(ERROR_CODE_PRODUCT_NOT_FOUND, ERROR_MESSAGE_PRODUCT_NOT_FOUND);

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.ok(updatedProduct).build();
    }

    @Override
    public Response deleteProduct(long id) {
        try {
            productRepository.deleteProduct(id);
        } catch (ProductNotFoundException e) {
            ErrorResponse errorResponse =
                    new ErrorResponse(ERROR_CODE_PRODUCT_NOT_FOUND, ERROR_MESSAGE_PRODUCT_NOT_FOUND);

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.ok().build();
    }
}