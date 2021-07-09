package controllers.dogs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Dog;

/**
 * Servlet implementation class DogsNewServlet
 */
@WebServlet("/dogs/new")
public class DogsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogsNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dog d = new Dog();
        String _token = request.getSession().getId();

        request.setAttribute("dog", d);
        request.setAttribute("_token", _token);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dogs/new.jsp");
        rd.forward(request, response);
    }

}
