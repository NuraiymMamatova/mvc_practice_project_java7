package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Comment;
import peaksoft.model.House;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Comment> getAllComment(Long id) {
        return entityManager.createQuery("select c from Comment c where c.house.id = :id", Comment.class).setParameter("id", id).getResultList();
    }

    public void saveComment(Long id, Comment comment) {
        House house = entityManager.find(House.class, id);
        house.addComment(comment);
        comment.setHouse(house);
        entityManager.merge(comment);
    }

}
