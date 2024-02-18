package Weavin.Controllers;

import Weavin.Entities.MarketPost;
import Weavin.Repositories.MarketPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

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

    // Delete MarketPost REST API
    @DeleteMapping("{marketPostId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMarketPost(@PathVariable int marketPostId) {
        MarketPost marketPost = marketPostRepository.findById(marketPostId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MarketPost not found with id: " + marketPostId));
        marketPostRepository.delete(marketPost);
    }
}
