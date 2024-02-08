package Weavin.Controllers;
import Weavin.Entities.ForumPost;
import Weavin.Repositories.ForumPostRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
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
    private ForumPostRepository forumPostRepository;
    @PostMapping
    public ResponseEntity<ForumPost> createForumPost(@RequestBody ForumPost forumPost) {
        ForumPost forumPost1 = this.forumPostRepository.save(forumPost);
        return new ResponseEntity<> (forumPost1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumPost> getForumPostById(@PathVariable("id") Integer id) {
        ForumPost forumPost = this.forumPostRepository.findById(id).get();
        return new ResponseEntity<>(forumPost, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<ForumPost>> getAllForumPosts() {
        Iterable<ForumPost> forumPosts = forumPostRepository.findAll();
        return new ResponseEntity<>(forumPosts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumPost> updateForumPost(@PathVariable("id") int id,
                                                     @RequestBody ForumPost forumPost){
        forumPost.setId(id);
        Optional<ForumPost> existingForumPost = forumPostRepository.findById(forumPost.getId());
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