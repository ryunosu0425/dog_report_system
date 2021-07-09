package controllers.dogs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Dog;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class DogsCreateServlet
 */
@WebServlet("/dogs/create")
@MultipartConfig(maxFileSize = 10485760)

public class DogsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Dog d = new Dog();

            d.setUser((User)request.getSession().getAttribute("login_user"));

            d.setAge(request.getParameter("age"));
            d.setDog_name(request.getParameter("dog_name"));
            d.setDog_type(request.getParameter("dog_type"));
            d.setSex(request.getParameter("sex"));

            Timestamp crrentTime = new Timestamp(System.currentTimeMillis());
            d.setCreated_at(crrentTime);

            Part part = request.getPart("image");
            int file_id = 0;

            InputStream inpStream = part.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            int c = 0;
            while((c = inpStream.read()) != -1) {
                outStream.write(c);
            }

            byte[] bytes = outStream.toByteArray();

            d.setImage(bytes);

            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();

            file_id = d.getId();
            em.close();

            request.getSession().setAttribute("file_id", file_id);
            response.sendRedirect(request.getContextPath() + "/users/show");
        }

    }

}
