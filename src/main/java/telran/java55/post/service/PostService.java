package telran.java55.post.service;

import java.util.List;

import telran.java55.post.dto.DateperiodDto;
import telran.java55.post.dto.NewCommentDto;
import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PostDto;

public interface PostService {

	PostDto addNewPost(String author, NewPostDto newPostDto);

	PostDto findPostById(String id);

	PostDto removePost(String id);

	PostDto updatePost(String id, NewPostDto newPostDto);

	PostDto addLike(String id, NewPostDto PostDto);

	List<PostDto> findPostsByAuthor(String author);

	PostDto addComment(String id, String author, NewCommentDto newCommentDto);

	Iterable<PostDto> findPostsByPeriod(DateperiodDto dateperiodDto);

	Iterable<PostDto> findPostsByTags(List<String> tags);

}
