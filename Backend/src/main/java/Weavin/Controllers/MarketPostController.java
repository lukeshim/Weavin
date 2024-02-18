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
@RequestMapping("/marketposts")
public class MarketPostController {

    @Autowired
    private MarketPostRepository marketPostRepository;

    // Create MarketPost REST API
    @PostMapping
    public void createMarketPost(@RequestBody MarketPost marketPost) {
        marketPostRepository.save(marketPost);
    }

    // Get MarketPost by id REST API
    @GetMapping("/{marketPostId}")
    public MarketPost getMarketPostById(@PathVariable int marketPostId) {
        return marketPostRepository.findById(marketPostId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MarketPost not found with id: " + marketPostId));
    }

    // Get All MarketPosts REST API
    @GetMapping
    public Iterable<MarketPost> getAllMarketPosts() {
        return marketPostRepository.findAll();
    }

    // Update MarketPost REST API
    @PutMapping("/{marketPostId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMarketPost(@PathVariable int marketPostId, @RequestBody MarketPost marketPost) {
        MarketPost existingMarketPost = marketPostRepository.findById(marketPostId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MarketPost not found."));

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MarketPost not found."));
        marketPostRepository.delete(marketPost);
    }
}
