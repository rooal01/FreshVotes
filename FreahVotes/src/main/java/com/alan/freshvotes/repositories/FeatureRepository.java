package com.alan.freshvotes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.freshvotes.domain.Feature;
import com.alan.freshvotes.domain.Product;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer>{
	
List<Feature> findAllByProduct(Product product);
}
