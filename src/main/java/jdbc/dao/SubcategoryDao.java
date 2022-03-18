package jdbc.dao;

import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubcategoryDao {

    private final EntityManager em;

    public SubcategoryDao(EntityManager em){
        this.em = em;
    }

    public List<SubCategory> getSubcategory(){
        String sql = "SELECT s FROM SubCategory s";
        return em.createQuery(sql, SubCategory.class)
                .getResultList();
    }

    public SubCategory getSubcategoryByCode(String code){
        String sql = "SELECT s FROM SubCategory s WHERE s.code = :code ";
        return em.createQuery(sql, SubCategory.class)
                .setParameter("code", code)
                .getSingleResult();
    }

}
