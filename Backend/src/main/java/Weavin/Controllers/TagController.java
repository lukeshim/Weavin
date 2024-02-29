package Weavin.Controllers;

import Weavin.Entities.ForumPost;
import Weavin.Entities.MarketPost;
import Weavin.Entities.Tag;
import Weavin.Enums.Field;
import Weavin.Repositories.ForumPostRepository;
import Weavin.Repositories.MarketPostRepository;
import Weavin.Repositories.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TagController {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private MarketPostRepository marketPostRepository;
    @Autowired
    private ForumPostRepository forumPostRepository;

    // PUT request to update tags under a market post
    @PutMapping("marketposts/{marketPostId}/tags")
    @ResponseStatus(HttpStatus.OK)
    private void updateMarketPostTags(@PathVariable("marketPostId") Integer id, @RequestBody List<Field> fieldList) {
        Optional<MarketPost> marketPostOptional = this.marketPostRepository.findById(id);
        if (marketPostOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market post not found.");
        }
        MarketPost marketPost = marketPostOptional.get();
        List<Tag> existingTagList = this.tagRepository.findByMarketPost(marketPost);
        // If there are any tags to be deleted
        for (int i = 0; i < existingTagList.size(); i++) {
            Tag tag = existingTagList.get(i);
            if (!fieldList.contains(tag.getField())) {
                this.tagRepository.delete(tag);
            }
        }
        // If there are any tags to be added
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            Optional<Tag> tag = this.tagRepository.findByMarketPostAndField(marketPost, field);
            if (tag.isEmpty()) {
                Tag tagToBeSaved = new Tag();
                tagToBeSaved.setMarketPost(marketPost);
                tagToBeSaved.setField(field);
                this.tagRepository.save(tagToBeSaved);
            }
        }
    }

    // PUT request to update tags under a forum post
    @PutMapping("forumposts/{forumPostId}/tags")
    @ResponseStatus(HttpStatus.OK)
    private void updateForumPostTags(@PathVariable("forumPostId") Integer id, @RequestBody List<Field> fieldList) {
        Optional<ForumPost> forumPostOptional = this.forumPostRepository.findById(id);
        if (forumPostOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum post not found.");
        }
        ForumPost forumPost = forumPostOptional.get();
        List<Tag> existingTagList = this.tagRepository.findByForumPost(forumPost);
        // If there are any tags to be deleted
        for (int i = 0; i < existingTagList.size(); i++) {
            Tag tag = existingTagList.get(i);
            if (!fieldList.contains(tag.getField())) {
                this.tagRepository.delete(tag);
            }
        }
        // If there are any tags to be added
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            Optional<Tag> tag = this.tagRepository.findByForumPostAndField(forumPost, field);
            if (tag.isEmpty()) {
                Tag tagToBeSaved = new Tag();
                tagToBeSaved.setForumPost(forumPost);
                tagToBeSaved.setField(field);
                this.tagRepository.save(tagToBeSaved);
            }
        }
    }
}