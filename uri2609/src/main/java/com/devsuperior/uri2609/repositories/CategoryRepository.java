package com.devsuperior.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(nativeQuery = true, value = "SELECT c.name, SUM(p.amount) "
			+ "FROM  products p "
			+ "INNER JOIN categories c ON c.id = p.id_categories "
			+ "GROUP BY c.name")
	List<CategorySumProjection> search1();
	
	
	@Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(obj.category.name, SUM(obj.amount)) "
			+ "FROM  Product obj "
			+ "GROUP BY obj.category.name")
	List<CategorySumDTO> search2();
	
	

}
