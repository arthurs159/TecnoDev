package validator;

import tecnodev.category.Category;
import tecnodev.subCategory.SubCategory;

import java.util.List;

public class Filters {

    public static Category filterCategoriesByCode(List<Category> categories, String categoryCode) {
        return categories.stream()
                .filter(category -> category.getCode().equalsIgnoreCase(categoryCode))
                .findFirst().orElse(null);
    }

    public static SubCategory filterSubCategoryByCode(List<SubCategory> subCategory, String subCategoryCode) {
        return subCategory.stream()
                .filter(subCat -> subCat.getCode().equalsIgnoreCase(subCategoryCode))
                .findFirst().orElse(null);
    }

}
