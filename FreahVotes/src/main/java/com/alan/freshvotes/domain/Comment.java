package com.alan.freshvotes.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Comment {
	
	//Note this is a composite primary key made up of the user and feature primary keys. In this table they will be called user_id and feature_id
	//this means you cannot have the same combination of user_id and feature_id in the table. So a user can only comment on a feature once.

	private Long id;
	private String text;
	private User user;
	private Feature feature;
	private List<Comment> childcomments = new ArrayList<>();
	private Comment comment;
	private Date createDate;
	private Boolean isread;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
	return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(length=5000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@ManyToOne
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JsonIgnore //Because a feature can have a comment, which can have a feature, which can have a comment and so on.
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	@OneToMany(mappedBy="comment")
	public List<Comment> getChildcomments() {
		return childcomments;
	}
	
	
	public void setChildcomments(List<Comment> chilccomments) {
		this.childcomments = chilccomments;
	}
	
	@ManyToOne
	@JoinColumn(name="comment_id", nullable=true)
	@JsonIgnore
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Boolean getIsread() {
		return isread;
	}
	public void setIsread(Boolean isread) {
		this.isread = isread;
	}

	

	
	

}
