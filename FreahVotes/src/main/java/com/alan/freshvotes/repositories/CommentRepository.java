package com.alan.freshvotes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.freshvotes.domain.Comment;
import com.alan.freshvotes.domain.Feature;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByFeatureId(Integer featureId);
	Optional<Comment> findById(Long commentId);

}
