package controllers.dogs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Dog;
import models.validators.DogValidator;
import utils.DBUtil;

/**
 * Servlet implementation class DogUpdateServlet
 */
@WebServlet("/dogs/update")
@MultipartConfig(maxFileSize = 10485760)

public class DogUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String _token = getParamVal(request.getPart("_token"));
        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();

            Dog d = em.find(Dog.class, (Integer) request.getSession().getAttribute("dog_id"));

            d.setAge(getParamVal(request.getPart("age")));
            d.setDog_name(getParamVal(request.getPart("dog_name")));
            d.setDog_type(getParamVal(request.getPart("dog_type")));
            d.setSex(getParamVal(request.getPart("sex")));

            Part part = request.getPart("image");

            InputStream inpStream = part.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            int c = 0;
            while ((c = inpStream.read()) != -1) {
                outStream.write(c);
            }

            byte[] bytes = outStream.toByteArray();


            if (bytes != d.getImage()) {

                d.setImage(bytes);
            }


            List<String> errors = DogValidator.validate(d);
            if (errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);
                request.setAttribute("dog", d);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dogs/edit.jsp");
                rd.forward(request, response);
            } else {

                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("dog_id");

                response.sendRedirect(request.getContextPath() + "/users/show");

            }

        }
    }

    private String getParamVal(Part part) {
        if (part.getContentType() == null) { // INPUTの文字列データはContentTypeがnull
            try {
                InputStream inputStream = part.getInputStream();
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
                return bufReader.lines().collect(Collectors.joining());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
