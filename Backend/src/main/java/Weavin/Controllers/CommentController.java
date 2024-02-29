package Weavin.Controllers;

import Weavin.Entities.Comment;
import Weavin.Entities.ForumPost;
import Weavin.Entities.MarketPost;
import Weavin.Repositories.CommentRepository;
import Weavin.Repositories.ForumPostRepository;
import Weavin.Repositories.MarketPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@ResponseStatus(HttpStatus.CREATED)
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MarketPostRepository marketPostRepository;
    @Autowired
    private ForumPostRepository forumPostRepository;

    // GET request to get all comments in a particular market post
    @GetMapping("/marketposts/{marketPostId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllCommentsByMarketPostId(@PathVariable Integer marketPostId) {
        Optional<MarketPost> marketPostOptional = this.marketPostRepository.findById(marketPostId);
        if (marketPostOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market post not found.");
        }
        MarketPost marketPost = marketPostOptional.get();
        List<Comment> commentList = marketPost.getCommentList();
        commentList.replaceAll(comment -> comment.isReportStatus() ? generatedReportedComment() : comment);
        return commentList;
    }

    // GET request to get all comments in a particular forum post
    @GetMapping("/forumposts/{forumPostId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllCommentsByForumPostId(@PathVariable Integer forumPostId) {
        Optional<ForumPost> forumPostOptional = this.forumPostRepository.findById(forumPostId);
        if (forumPostOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum post not found.");
        }
        ForumPost forumPostToBeFound = forumPostOptional.get();
        List<Comment> commentList = forumPostToBeFound.getCommentList();
        commentList.replaceAll(comment -> comment.isReportStatus() ? generatedReportedComment() : comment);
        return commentList;
    }

    // GET request to get a specific comment by id
    @GetMapping("comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Comment findCommentById(@PathVariable Integer commentId) {
        Optional<Comment> commentOptional = this.commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found.");
        }
        Comment commentToBeFound = commentOptional.get();
        if (commentToBeFound.isReportStatus()) {
            return generatedReportedComment();
        } else {
            return commentToBeFound;
        }
    }

    // POST request to create a comment under a market post
    @PostMapping("marketposts/{marketPostId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    private void createMarketPostComment(@PathVariable("marketPostId") Integer id, @RequestBody Comment comment) {
        Optional<MarketPost> marketPostOptional = this.marketPostRepository.findById(id);
        if (marketPostOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market post not found.");
        }
        MarketPost marketPost = marketPostOptional.get();
        comment.setMarketPost(marketPost);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        this.commentRepository.save(comment);
    }

    // POST request to create a comment under a forum post
    @PostMapping("forumposts/{forumPostId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    private void createForumPostComment(@PathVariable("forumPostId") Integer id, @RequestBody Comment comment) {
        Optional<ForumPost> forumPostOptional = this.forumPostRepository.findById(id);
        if (forumPostOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market post not found.");
        }
        ForumPost forumPost = forumPostOptional.get();
        comment.setForumPost(forumPost);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        this.commentRepository.save(comment);
    }

    // DELETE request to delete a comment
    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Integer id) {
        Optional<Comment> commentOptional = this.commentRepository.findById(id);
        if (commentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found.");
        }
        Comment commentToBeDeleted = commentOptional.get();
        this.commentRepository.delete(commentToBeDeleted);
    }

    // PUT request to update a comment
    @PutMapping("/comments/{commentId}")
    public void updateComment(@PathVariable("commentId") int id, @RequestBody Comment comment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found.");
        }
        Comment updatedComment = existingComment.get();
        updatedComment.setUpdatedAt(new Date());
        updatedComment.setBody(comment.getBody());
        commentRepository.save(updatedComment);
    }

    // PUT request to add like to a comment
    @PutMapping("/comments/{commentId}/like")
    public void addLikes(@PathVariable("commentId") int id) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found.");
        }
        Comment updatedComment = existingComment.get();
        updatedComment.setLikes(updatedComment.getLikes() + 1);
        commentRepository.save(updatedComment);
    }

    // PUT request to report a comment
    @PutMapping("/comments/{commentId}/report")
    @ResponseStatus(HttpStatus.OK)
    public void reportComment(@PathVariable("commentId") Integer id) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found.");
        }
        Comment updatedComment = existingComment.get();
        updatedComment.setReports(updatedComment.getReports() + 1);
        if (updatedComment.getReports() > 15) {
            updatedComment.setReportStatus(true);
        }
        this.commentRepository.save(updatedComment);

    }

    // Generate blank comment with report flag
    private Comment generatedReportedComment() {
        Comment reportedComment = new Comment();
        reportedComment.setReportStatus(true);
        return reportedComment;
    }
}

