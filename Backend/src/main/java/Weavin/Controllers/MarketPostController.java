package Weavin.Controllers;

import Weavin.Entities.ForumPost;
import Weavin.Entities.MarketPost;
import Weavin.Repositories.MarketPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class MarketPostController {

    @Autowired
    private MarketPostRepository marketPostRepository;

    // GET request to get all market posts
    @GetMapping("/marketposts")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<MarketPost> getAllMarketPosts() {
        return marketPostRepository.findAll();
    }

    // GET request to get specific market post by id
    @GetMapping("/marketposts/{marketPostId}")
    @ResponseStatus(HttpStatus.OK)
    public MarketPost getMarketPostById(@PathVariable int marketPostId) {
        MarketPost marketPost = marketPostRepository.findById(marketPostId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MarketPost not found with id: " + marketPostId));
        marketPost.setViews(marketPost.getViews() + 1);
        this.marketPostRepository.save(marketPost);
        return marketPost;
    }

    // GET R
    @PostMapping
    public void createMarketPost(@RequestBody MarketPost marketPost) {
        marketPostRepository.save(marketPost);
    }




    // Update MarketPost REST API
    @PutMapping("/{marketPostId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMarketPost(@PathVariable int marketPostId, @RequestBody MarketPost marketPost) {
        MarketPost existingMarketPost = marketPostRepository.findById(marketPostId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MarketPost not found with id: " + marketPostId));

        existingMarketPost.setProductName(marketPost.getProductName());
        existingMarketPost.setDescription(marketPost.getDescription());
        existingMarketPost.setUpdatedAt(new Date());
        existingMarketPost.setUpdated(true);
        existingMarketPost.setPhoto(marketPost.getPhoto());
        existingMarketPost.setPrice(marketPost.getPrice());
        existingMarketPost.setReports(marketPost.getReports());
        existingMarketPost.setReportStatus(marketPost.isReportStatus());
        existingMarketPost.setViews(marketPost.getViews());
        existingMarketPost.setLikes(marketPost.getLikes());
        existingMarketPost.setSold(marketPost.isSold());
        existingMarketPost.setStock(marketPost.getStock());
        existingMarketPost.setCommentList(marketPost.getCommentList());

        marketPostRepository.save(existingMarketPost);
    }
    //Add likes on the market post
    @PutMapping("/{marketPostId}/likes")
    public void addLikes(@PathVariable int id) {
        Optional<MarketPost> existingMarketPost = marketPostRepository.findById(id);
        if (existingMarketPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Market post not found.");
        }
        MarketPost updatedMarketPost = existingMarketPost.get();
        updatedMarketPost.setLikes(updatedMarketPost.getLikes() + 1);
        marketPostRepository.save(updatedMarketPost);
    }

    // Delete MarketPost REST API
    @DeleteMapping("{marketPostId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMarketPost(@PathVariable int marketPostId) {
        MarketPost marketPost = marketPostRepository.findById(marketPostId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MarketPost not found with id: " + marketPostId));
        marketPostRepository.delete(marketPost);
    }
}
