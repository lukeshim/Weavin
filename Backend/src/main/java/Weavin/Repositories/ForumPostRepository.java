package Weavin.Repositories;

import Weavin.Entities.ForumPost;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ForumPostRepository extends CrudRepository<ForumPost, Integer> {

}