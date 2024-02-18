package Weavin.Controllers;

import Weavin.Entities.Comment;
import Weavin.Entities.ForumPost;
import Weavin.Entities.User;
import Weavin.Enums.Presence;
import Weavin.Enums.ReportStatus;
import Weavin.Repositories.CommentRepository;
import Weavin.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@RestController
@ResponseStatus(HttpStatus.CREATED)
@RequestMapping(value={"/forumposts/{forumpostid}/comments", "/marketposts/{marketpostid}/comments"})
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment findById(@PathVariable Integer id) {
        Optional<Comment> commentOptional = this.commentRepository.findById(id);
        if (commentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comment does not exist");
        }
        Comment commentToBeFound = commentOptional.get();
        return commentToBeFound;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private void createComment(@RequestBody Comment comment) {
        this.commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Integer id) {
        Optional<Comment> commentOptional = this.commentRepository.findById(id);
        if (commentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment does not exist");
        }
        Comment commentToBeDeleted = commentOptional.get();
        this.commentRepository.delete(commentToBeDeleted);
    }
    @PutMapping("/{id}")
    public void updateComment(@PathVariable("id") int id, @RequestBody Comment comment) {
        comment.setId(id);
        Optional<Comment> existingComment = commentRepository.findById(comment.getId());
        if (existingComment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found.");
        }
        Comment updatedComment = existingComment.get();
        updatedComment.setUpdatedAt(comment.getUpdatedAt());
        updatedComment.setLikes(comment.getLikes());
        updatedComment.setBody(comment.getBody());
        updatedComment.setReports(comment.getReports());
        commentRepository.save(updatedComment);
    }
}

