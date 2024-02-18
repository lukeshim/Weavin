package Weavin.Controllers;
import Weavin.Entities.ForumPost;
import Weavin.Entities.User;
import Weavin.Repositories.ForumPostRepository;
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
@AllArgsConstructor
@RequestMapping("/forumposts")
public class ForumPostController {

    @Autowired
    private ForumPostRepository forumPostRepository;

    @GetMapping
    public ResponseEntity<Iterable<ForumPost>> getForumPosts() {
        Iterable<ForumPost> forumPosts = forumPostRepository.findAll();
        return new ResponseEntity<>(forumPosts, HttpStatus.OK);
    }

    @GetMapping("/{forumPostId}")
    public ResponseEntity<ForumPost> getForumPostById(@PathVariable("forumPostId") Integer id) {
        Optional<ForumPost> forumPostOptional = this.forumPostRepository.findById(id);
        if (forumPostOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum post does not exist");
        }
        ForumPost forumPost = forumPostOptional.get();
        return new ResponseEntity<>(forumPost, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ForumPost> createForumPost(@RequestBody ForumPost forumPost) {
        ForumPost forumPost1 = this.forumPostRepository.save(forumPost);
        return new ResponseEntity<> (forumPost1, HttpStatus.CREATED);
    }

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
        updatedForumPost.setLikes(forumPost.getLikes());
        updatedForumPost.setCommentList(forumPost.getCommentList());
        updatedForumPost.setTagList(forumPost.getTagList());
        updatedForumPost.setViews(forumPost.getViews());
        updatedForumPost.setReports(forumPost.getReports());
        return new ResponseEntity<>(updatedForumPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteForumPost(@PathVariable("id") int id) {
        forumPostRepository.deleteById(id);
        return new ResponseEntity<>("Forum post successfully deleted!", HttpStatus.OK);
    }

}