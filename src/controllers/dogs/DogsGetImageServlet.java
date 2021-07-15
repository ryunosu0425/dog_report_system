package controllers.dogs;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Dog;
import utils.DBUtil;

/**
 * Servlet implementation class DogsGetImageServlet
 */
@WebServlet("/dogs/getImage")
public class DogsGetImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogsGetImageServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Dog img = em.find(Dog.class, Integer.parseInt(request.getParameter("id")));

        byte[] bytes = img.getImage();
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
    }

}
