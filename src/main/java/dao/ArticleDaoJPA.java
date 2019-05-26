package dao;

import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ArticleDaoJPA implements Dao<Article, NewArticle>{

    private EntityManager em;


    public ArticleDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Article> getAll() {
        em.getTransaction().begin();
        Query q = em.createQuery("From ArticleEntity");
        List<Article> result = (List<Article>) q.getResultStream().map(e -> new Article((ArticleEntity)e)).collect(Collectors.toList());
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Optional<Article> get(long id) {
       return getEntity(id).map(a -> new Article(a));


        //return new Article(getEntity(id));
//        em.getTransaction().begin();
//        ArticleEntity a = (ArticleEntity) em.createNativeQuery("Select a from ArticleEntity a where id = " +
//                id).getSingleResult();
//        em.getTransaction.commit();
//        return new Article(a);
    }

    @Override
    public void save(NewArticle obj) {

        ArticleEntity ae = new ArticleEntity(obj);
        em.getTransaction().begin();
        em.persist(ae);
        em.getTransaction().commit();

    }

    @Override
    public void delete(long id) {

        //jakby się jednak domagało transakcji
//        getEntity(id).ifPresent(a ->{
//            em.getTransaction().begin();
//            em.remove(a) ;
//        em.getTransaction().commit();
//        });

        getEntity(id).ifPresent(a ->em.remove(a));


    }

    @Override
    public void update(long id) {
        throw new UnsupportedOperationException();
    }


    private Optional<ArticleEntity> getEntity(long id){

        try {
           return Optional.of(em.createQuery("Select a from ArticleEntity a where id = :id", ArticleEntity.class)
                    .setParameter("id", id)
                    .getSingleResult());
        }catch (Exception e){
            return Optional.empty();
        }


    }
}