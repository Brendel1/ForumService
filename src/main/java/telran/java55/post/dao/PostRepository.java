package telran.java55.post.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java55.post.model.Post;

public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByAuthorIgnoreCase(String author);

}
