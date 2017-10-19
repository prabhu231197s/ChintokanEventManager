package madmaxstudios.com.chintokaneventmanager.Entities;

/**
 * Created by Prabhu Sivanandam on 16-Oct-17.
 */

public class CategoryEntity {
    int CategoryId;
    String Category;

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public CategoryEntity() {

    }

    public CategoryEntity(int categoryId, String category) {

        CategoryId = categoryId;
        Category = category;
    }
}
