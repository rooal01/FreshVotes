package com.alan.freshvotes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.freshvotes.domain.Feature;
import com.alan.freshvotes.domain.Product;
import com.alan.freshvotes.repositories.FeatureRepository;
import com.alan.freshvotes.repositories.ProductRepository;

@Service
public class FeatureService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private FeatureRepository featureRepo;
	
	public Feature createFreature(Long productId) {
		
		Feature feature = new Feature();
		
		Optional<Product> productOpt = productRepo.findByIdWithUser(productId);
		
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			feature.setProduct(product);
			product.getFeatures().add(feature);
			
			return featureRepo.save(feature);
		}

		
		return feature;
		
	}

	public Optional<Feature> GetById(Integer featureId) {
		
//		Optional<Feature> featureOpt = featureRepo.findById(featureId);
//		if(featureOpt.isPresent()){
//			Feature feature= featureOpt.get();
//			return feature;
//		}
//		//not sure here think about it
		return featureRepo.findById(featureId);

	
	}
	
public Feature save(Feature feature) {
		
		return featureRepo.save(feature);

	
	}
}
