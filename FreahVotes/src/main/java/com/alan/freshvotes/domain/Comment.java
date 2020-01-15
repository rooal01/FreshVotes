package com.alan.freshvotes.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Comment {
	
	//Note this is a composite primary key made up of the user and feature primary keys. In this table they will be called user_id and feature_id
	//this means you cannot have the same combination of user_id and feature_id in the table. So a user can only comment on a feature once.
	@EmbeddedId
	public CommentId getPk() {
		return pk;
	}
	public void setPk(CommentId pk) {
		this.pk = pk;
	}
	
	@Column(length=5000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private CommentId pk;
	private String text;
	
	

}
