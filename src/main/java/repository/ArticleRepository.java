package repository;

import dao.Dao;
import entity.Article;
import entity.NewArticle;
import io.vavr.collection.List;
import java.util.Optional;


public class ArticleRepository {

    private Dao<Article, NewArticle>dao;

    public ArticleRepository(Dao<Article, NewArticle> dao) {
        this.dao = dao;
    }

    public ArticleRepository() {

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

    public void changeTitle(long id, String title){
        dao.get(id).map(old-> new Article (old.content, title, old.id, old.created))
                .ifPresent(a->dao.update(a));
    }

    public List<Article>getLimited(int from, int to){
        return dao.getLimited(from, to);
    }

}
