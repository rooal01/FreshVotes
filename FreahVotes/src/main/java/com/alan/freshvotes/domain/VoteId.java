package com.alan.freshvotes.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

//This creates the required foreign key relationship between the vote table the user and feature tables
//for the features the feature_id in the votes table gets mapped to the id in the features table
//for the users the user_id in the votes table gets mapped to the id in the users table.
//the user_id and feature_id entries are automatically created and together create a composite primary key for the vote table.
@Embeddable
public class VoteId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4670923777854974693L;
	private User user;
	private Feature feature;
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	
	

}
