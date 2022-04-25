package br.com.tecnodev.projections;

import java.util.List;

public interface CategoryLoginProjection {

    String getName();
    String getCode();
    String getImageUrl();
    Integer getOrderInSystem();
    List<SubcategoryLoginProjection> getSubProjection();
}
