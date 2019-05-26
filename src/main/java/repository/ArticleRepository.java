package repository;

import dao.Dao;
import entity.Article;
import entity.NewArticle;

import java.util.List;
import java.util.Optional;


public class ArticleRepository {

    private Dao<Article, NewArticle>dao;

    public ArticleRepository(Dao<Article, NewArticle> dao) {
        this.dao = dao;
    }

    public void addArticle(NewArticle na){
        dao.save(na);
    }

    public List<Article> getAll(){
        return dao.getAll();
    }

    public Optional<Article> get(long id){
        return dao.get(id);
    }

    public void remove(long id){
        dao.delete(id);

    }

}
