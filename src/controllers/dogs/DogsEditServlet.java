package controllers.dogs;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Dog;
import utils.DBUtil;

/**
 * Servlet implementation class DogsEditServlet
 */
@WebServlet("/dogs/edit")
public class DogsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogsEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Dog d = em.find(Dog.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("dog", d);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("dog_id", d.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dogs/edit.jsp");
        rd.forward(request, response);
    }

}
