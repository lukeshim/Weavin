package Weavin.Controllers;
import Weavin.Entities.ForumPost;
import Weavin.Entities.User;
import Weavin.Repositories.ForumPostRepository;
import Weavin.Repositories.UserRepository;
import jakarta.servlet.annotation.HttpConstraint;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ForumPostController {

    @Autowired
    private ForumPostRepository forumPostRepository;

    @Autowired
    private UserRepository userRepository;

    // GET request to get all forum posts
    @GetMapping("/forumposts")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ForumPost> getForumPosts() {
        return forumPostRepository.findAll();
    }

    // GET request to get specific forum post by id
    @GetMapping("/forumposts/{forumPostId}")
    @ResponseStatus(HttpStatus.OK)
    public ForumPost getForumPostById(@PathVariable("forumPostId") Integer id) {
        Optional<ForumPost> forumPostOptional = this.forumPostRepository.findById(id);
        if (forumPostOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum post does not exist");
        }
        return forumPostOptional.get();
    }

    // POST request to post a new forum by specified user
    @PostMapping("/users/{userId}/forumposts/{forumPostId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createForumPost(@PathVariable int userId, @RequestBody ForumPost forumPost) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = userOptional.get();
        forumPost.setUser(user);
        this.forumPostRepository.save(forumPost);
    }

    // PUT request to update forum post content
    @PutMapping("/{id}")
    public ResponseEntity<ForumPost> updateForumPost(@PathVariable int id, @RequestBody ForumPost forumPost) {
        Optional<ForumPost> existingForumPost = forumPostRepository.findById(id);
        if (existingForumPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum post not found.");
        }
        ForumPost updatedForumPost = existingForumPost.get();
        updatedForumPost.setTitle(forumPost.getTitle());
        updatedForumPost.setUpdated(true);
        updatedForumPost.setBody(forumPost.getBody());
        updatedForumPost.setField(forumPost.getField());
        return new ResponseEntity<>(updatedForumPost, HttpStatus.OK);
    }

    // DELETE request to delete forum post
    @DeleteMapping("/forumposts/{id}")
    public ResponseEntity<String> deleteForumPost(@PathVariable("id") int id) {
        forumPostRepository.deleteById(id);
        return new ResponseEntity<>("Forum post successfully deleted!", HttpStatus.OK);
    }

}