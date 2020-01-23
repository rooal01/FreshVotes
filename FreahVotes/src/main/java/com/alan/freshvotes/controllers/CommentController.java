package com.alan.freshvotes.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alan.freshvotes.domain.Comment;
import com.alan.freshvotes.domain.Feature;
import com.alan.freshvotes.domain.User;
import com.alan.freshvotes.repositories.CommentRepository;
import com.alan.freshvotes.service.FeatureService;

@Controller
@RequestMapping("/products/{productId}/features/{featureId}/comments") //This now become the root for this controller
public class CommentController {
	//static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	@Autowired
	public CommentRepository commentRepo;
	
	@Autowired
	private FeatureService featureService;
	
	@GetMapping("")
	@ResponseBody  //return data not a view, REST endpoint
	public List<Comment> getComments (@PathVariable Integer featureId) {
		
		return commentRepo.findByFeatureId(featureId);
	}
	
	@PostMapping("")
	public String addComments (@RequestParam("parentcomment") Long parentComment, @AuthenticationPrincipal User user, Feature feature ,Comment comment, @PathVariable Integer featureId, @PathVariable Long productId, ModelMap model) {
		
		
		//LocalDateTime now = LocalDateTime.now(); 
		//Date date = (Date) dtf.parse(now.toString());
		Date date = new Date();
		
		
		comment.setUser(user);
		Optional<Feature> linkedFeature = featureService.GetById(featureId);
		if(linkedFeature.isPresent()){
			comment.setFeature(linkedFeature.get());
		}

//		featureService.save(feature);
//		newcomment.setText(model.get(comment.text));
		comment.setCreateDate(date);
			if(parentComment != null) {
			Optional<Comment> linkedComment = commentRepo.findById(parentComment);
			
			if(linkedComment.isPresent()) {
				
				comment.setComment(linkedComment.get());
			}
			
		}
		
		//TO DO create service to do this in
		
		commentRepo.save(comment);
		
		
		return "redirect:/products/"+productId+"/features/"+featureId;
	}

}
