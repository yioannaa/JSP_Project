package dao;

import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleDaoJPA implements Dao<Article, NewArticle>{

    private EntityManager em;


    public ArticleDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Article> getAll() {
        em.getTransaction().begin();
        Query q = em.createQuery("From ArticleEntity ");
        List<Article> result = (List<Article>) q.getResultStream().map(e -> new Article((ArticleEntity)e)).collect(Collectors.toList());
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Article get(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(NewArticle obj) {

        ArticleEntity ae = new ArticleEntity(obj);
        em.getTransaction().begin();
        em.persist(ae);
        em.getTransaction().commit();

    }

    @Override
    public void delete(Article obj) {

    }

    @Override
    public void update(Article obj) {
        throw new UnsupportedOperationException();
    }
}