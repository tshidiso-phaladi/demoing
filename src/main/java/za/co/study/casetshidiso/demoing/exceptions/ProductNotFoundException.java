package za.co.study.casetshidiso.demoing.exceptions;

public class ProductNotFoundException extends Exception{
    private long productId;

    public ProductNotFoundException(long productId) {
        this.productId = productId;
    }

    @Override
    public String getMessage() {
        return "Product with id " + productId + " not found";
    }
}
