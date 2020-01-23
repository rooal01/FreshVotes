package com.alan.freshvotes.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alan.freshvotes.domain.Comment;
import com.alan.freshvotes.domain.Feature;
import com.alan.freshvotes.domain.User;
import com.alan.freshvotes.repositories.CommentRepository;
import com.alan.freshvotes.repositories.FeatureRepository;
import com.alan.freshvotes.service.FeatureService;


@Controller
@RequestMapping("/products/{productId}/features") //This now become the root for this controller
public class FeatureController {
	
	@Autowired
	private FeatureService featureService;
	
/*	@Autowired
	private CommentRepository commentRepo;*/


	@PostMapping("") //this maps to -> /products/{productId/features
	public String createFeature(@AuthenticationPrincipal User user,  @PathVariable Long productId) {
		
		Feature feature = featureService.createFreature(productId, user);
		
		return "redirect:/products/"+productId+"/features/"+feature.getId();
		
	}
	

	@GetMapping("{featureId}")
	public String getFeature(@AuthenticationPrincipal User user,@PathVariable Long productId, @PathVariable Integer featureId, ModelMap model){
		
		Optional<Feature> featureOpt = featureService.GetById(featureId);
		
		if(featureOpt.isPresent()){
			
			model.put("feature", featureOpt.get());
// These following is the model we use in the view to display the features comments
//			model.put("comments", featureOpt.get().getComments());
			model.put("comments", featureService.removeDuplicates(featureOpt.get().getComments()));
//			model.put("comments",commentRepo.findByFeatureId(featureOpt.get().getId()));

			System.out.println(featureOpt.get().getComments().toString());
			
		}
      
		//TODO: hOW TO HANDLE NO RETURNED FEATURE:MAYBE GO TO ANOTHER PAGE.
		//putting this on the model so we can hide information not allowed by this user in the front end.
		//I think its better to only return information allowed to the user so I will revisit all of the security issues created through the use of hidden fields in the frontend when the app is complete.
		model.put("user", user);
		Comment comment = new Comment();
		model.put("newcomment", comment);
		return "feature";
	}
	
	
	@PostMapping("{featureId}")
	public String updateFeature(@AuthenticationPrincipal User user, Feature feature,@PathVariable Long productId, @PathVariable Long featureId){
		
		feature.setUser(user);
		featureService.save(feature);
		return "redirect:/pname/"+feature.getProduct().getName();
//		return "redirect:/products/"+productId+"/features/"+feature.getId();
	}

}
