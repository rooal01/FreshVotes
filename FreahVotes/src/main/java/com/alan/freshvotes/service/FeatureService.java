package com.alan.freshvotes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.freshvotes.domain.Comment;
import com.alan.freshvotes.domain.Feature;
import com.alan.freshvotes.domain.Product;
import com.alan.freshvotes.domain.User;
import com.alan.freshvotes.repositories.FeatureRepository;
import com.alan.freshvotes.repositories.ProductRepository;

@Service
public class FeatureService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private FeatureRepository featureRepo;
	
	public Feature createFreature(Long productId, User user) {
		
		Feature feature = new Feature();
		
		Optional<Product> productOpt = productRepo.findByIdWithUser(productId);
		
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			//populate both sides of the relationship.
			feature.setProduct(product);
			product.getFeatures().add(feature);
			feature.setStatus("Pending Review");
			
			feature.setUser(user);
			user.getFeatures().add(feature);
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

public List<Comment> removeDuplicates(List<Comment> comments){
	
	//here we sort and reorder the comments to make it look like the json. need to see how we can use the JSON directly.

	List<Long> idList = new ArrayList<Long>();
	idList.add(99999999999999l);
	List<Comment> newcommentlist = new ArrayList<Comment>();
	for (Comment comment : comments){
	
//we only allow two level of sub comments for now.
		if (comment.getComment() != null){
			if (comment.getComment().getComment() != null){
				if(!idList.contains(comment.getComment().getComment().getId())){
					newcommentlist.add(comment.getComment().getComment());
				}
			idList.add(comment.getComment().getComment().getId());
			} else if(comment.getComment() != null) {
				if(!idList.contains(comment.getComment().getId())){
				newcommentlist.add(comment.getComment());
				}
				idList.add(comment.getComment().getId());
			}
			else {
				if(!idList.contains(comment.getId())){
					newcommentlist.add(comment);
				}
				idList.add(comment.getId());
			}
		} else if(comment.getComment() != null) {

			if(!idList.contains(comment.getComment().getId())){
				newcommentlist.add(comment);
			}
			idList.add(comment.getComment().getId());
		} else {
			if(!idList.contains(comment.getId())){
				newcommentlist.add(comment);
			}
			idList.add(comment.getId());
		}
		
	
	}
	
	//reverse the list so we always have the latest message first.
	Collections.reverse(newcommentlist);
	return newcommentlist;
}
}
