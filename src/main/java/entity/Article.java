package entity;

import java.time.LocalDateTime;

public class Article extends NewArticle {

    public long id;
    public LocalDateTime created;


    public Article(String content, String title, long id, LocalDateTime created) {
        super(content, title);
        this.id = id;
        this.created = created;
    }

    public Article(ArticleEntity en){
        super(en.getContent(), en.getTitle());
        this.id = en.getId();
        this.created = en.getCreated();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
