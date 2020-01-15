package com.alan.freshvotes.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Vote {
	
	
	private VoteId pk;
	
	//Note this is a composite primary key made up of the user and feature primary keys. In this table they will be called user_id and feature_id
	//this means you cannot have the same combination of user_id and feature_id in the table. So a user can only vote on a feature once.
	@EmbeddedId
	public VoteId getPk() {
		return pk;
	}

	public void setPk(VoteId pk) {
		this.pk = pk;
	}

	private Boolean upvote;

	public Boolean getUpvote() {
		return upvote;
	}

	public void setUpvote(Boolean upvote) {
		this.upvote = upvote;
	}

}
