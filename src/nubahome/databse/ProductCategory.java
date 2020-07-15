package nubahome.databse;

public class ProductCategory {
    int categoryID;
    String categoryName;

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProductCategory(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }


    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
