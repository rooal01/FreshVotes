package com.alan.freshvotes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alan.freshvotes.domain.Product;
import com.alan.freshvotes.domain.User;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	//Creating our own method and custom query to ensure the user id is retrieved when you get a product.
	
	@Query("select p from Product p"
			+ " join fetch p.user"
			+ " where p.id = :id")
	Optional<Product> findByIdWithUser(@Param("id") Long id);
	//Creating our own method to get products by user
	List<Product> findByUser(User user);
	
	Optional<Product> findByName(String name);
	

}
