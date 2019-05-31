package servlet;

import dao.ArticleDaoJPA;
import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import helper.Encoding;
import helper.Paginator;
import helper.Parse;
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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {

    public static final String ACTION_VIEW_ALL = "viewAll";
    public static final String ACTION_VIEW = "view";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_ADD = "add";
    public static final String ACTION_CHANGE_TITLE = "changeTitle";
    public static final String ACTION_UPDATE_TITLE = "updateTitle";
    public static final String ACTION = "action";
    public static final String ACTION_VIEW_LIMITED = "viewLimited";
    public static final String MAIN = "main";

    ArticleRepository repo;
    Paginator paginator;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");
        repo = new ArticleRepository(new ArticleDaoJPA((factory.createEntityManager())));
        paginator = new Paginator(repo.getAll().size(),5);

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter(ACTION);


        switch (action) {
            case ACTION_VIEW_ALL: {
                req.setAttribute("articles", repo.getAll().asJava());
                RequestDispatcher rd = req.getRequestDispatcher("view_articles.jsp");
                rd.forward(req, resp);
            }
            break;
            case ACTION_VIEW: {

                Parse.parseLong(req.getParameter("id")
                ).ifPresent(id -> repo.get(id)
                        .ifPresent(a -> req.setAttribute("article", a)));

                RequestDispatcher rd = req.getRequestDispatcher("view_article.jsp");
                rd.forward(req, resp);

            }
            break;
            case ACTION_DELETE: {
                Parse.parseLong(req.getParameter("id")).ifPresent(id -> repo.remove(id));
                System.out.println(req.getParameter("id"));
                resp.sendRedirect("article?" + ACTION + "=" + ACTION_VIEW_ALL);
            }
            break;
            case ACTION_ADD: {
                RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
                rd.forward(req, resp);
            }

            break;
            case ACTION_CHANGE_TITLE: {
                Parse.parseLong(req.getParameter("id")
                ).ifPresent(id -> repo.get(id)
                        .ifPresent(a -> {
                            req.setAttribute("article", a);
                        }));
                RequestDispatcher rd = req.getRequestDispatcher("change_title.jsp");
                rd.forward(req, resp);

            }
            break;
            case ACTION_VIEW_LIMITED: {
                usePaginator( paginator,  req,  resp);

            }
            break;
            case MAIN: {
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            }
            break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ACTION_ADD: {
                String title = Encoding.encode(req.getParameter("title"));
                String content = Encoding.encode(req.getParameter("content"));
                repo.addArticle(new NewArticle(content, title));
                resp.sendRedirect("article?" + ACTION + "=" + ACTION_VIEW_ALL);

                break;
            }
            case ACTION_UPDATE_TITLE: {
                String title = Encoding.encode(req.getParameter("title"));
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> repo.changeTitle(id, title));
                resp.sendRedirect("article?" + ACTION + "=" + ACTION_VIEW_ALL);
            }
            break;
        }
    }

    private void usePaginator(Paginator paginator, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Article> articleList = repo.getAll().asJava();
        preparePaging(req);
        paginator = new Paginator(articleList.size(), 5);
        req.setAttribute("page", paginator.getPage());
        req.setAttribute("articles", articleList
                .stream()
                .skip(paginator.getFrom())
                .limit(paginator.getTo() - paginator.getFrom())
                .collect(Collectors.toList()));
        RequestDispatcher rd = req.getRequestDispatcher("view_limited_articles.jsp");
        rd.forward(req, resp);
    }

    private void preparePaging(HttpServletRequest req) {
        String dir = req.getParameter("page");
        if ("prev".equals(dir)) {
            paginator.previousPage();
        }
        if ("next".equals(dir)) {
            paginator.nextPage();
        }
        if ("first".equals(dir)) {
            paginator.startPage();
        }
        if ("last".equals(dir)) {
            paginator.lastPage();
        }

    }
}
