package com.alan.freshvotes.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Vote {
	
	
	private VoteId pk;
	
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
