package servlet;

import dao.ArticleDaoJPA;
import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import repository.ArticleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {

    private EntityManager em;
    ArticleRepository repo;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");
        repo = new ArticleRepository(new ArticleDaoJPA((factory.createEntityManager())));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        repo.addArticle(new NewArticle("Super tekst", "Pierwszy"));
        repo.addArticle(new NewArticle("Jeszcze lepszy tekst", "Już drugi"));
        List<Article> list = repo.getAll();
        PrintWriter out = resp.getWriter();
        for (Article a : list) {
            out.println(a.content);
            out.println(a.title);
            out.println(a.created);
        }

    }
}
