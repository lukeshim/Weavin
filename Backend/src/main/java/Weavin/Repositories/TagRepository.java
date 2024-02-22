package Weavin.Repositories;

import Weavin.Entities.ForumPost;
import Weavin.Entities.MarketPost;
import Weavin.Entities.Tag;
import Weavin.Enums.Field;
import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Integer> {

    List<Tag> findByMarketPost(MarketPost marketPost);
    Optional<Tag> findByMarketPostAndField(MarketPost marketPost, Field field);

    List<Tag> findByForumPost(ForumPost forumPost);
    Optional<Tag> findByForumPostAndField(ForumPost forumPost, Field field);
}
