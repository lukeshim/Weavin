package Weavin.Repositories;

import Weavin.Entities.ForumPost;
import Weavin.Entities.User;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ForumPostRepository extends CrudRepository<ForumPost, Integer> {
    List<ForumPost> findByUser(User user);
}