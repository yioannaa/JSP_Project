package servlet;

import dao.ArticleDaoJPA;
import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import repository.ArticleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
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

    ArticleRepository repo;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");
        repo = new ArticleRepository(new ArticleDaoJPA((factory.createEntityManager())));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        repo.addArticle(new NewArticle("Super tekst", "Pierwszy"));
//        repo.addArticle(new NewArticle("Jeszcze lepszy tekst", "Już drugi"));
//        List<Article> list = repo.getAll();
//        PrintWriter out = resp.getWriter();
//        for (Article a : list) {
//            out.println(a.content);
//            out.println(a.title);
//            out.println(a.created);
//        }

//        RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
//        rd.forward(req,resp);

        String action = req.getParameter("action");

        switch (action) {
            case "viewAll": {
                req.setAttribute("articles", repo.getAll().asJava());
                RequestDispatcher rd = req.getRequestDispatcher("view_articles.jsp");
                rd.forward(req, resp);
            }
            break;
            case "view": {
                long id = Long.parseLong(req.getParameter("id"));
                req.setAttribute("article", repo.get(id));
                RequestDispatcher rd = req.getRequestDispatcher("view_article.jsp");
                rd.forward(req, resp);

            }
            break;
            case "delete":{
                long id = Long.parseLong(req.getParameter("id"));
                repo.remove(id);
            }
            break;
            case "add": {
                RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
                rd.forward(req, resp);
            }
            break;
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch(action){
            case "add":
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                repo.addArticle(new NewArticle(content,title));
                break;
        }
    }
}
