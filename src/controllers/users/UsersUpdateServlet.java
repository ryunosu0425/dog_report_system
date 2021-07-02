package controllers.users;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.UserValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class UsersUpdateServlet
 */
@WebServlet("/users/update")
public class UsersUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getParameter("_token"))){


        EntityManager em = DBUtil.createEntityManager();

        User u = em.find(User.class, (Integer)request.getSession().getAttribute("user_id"));

        Boolean codeDuplicateCheckFlag = true;
        if(u.getCode().equals(request.getParameter("code"))) {
            codeDuplicateCheckFlag = false;
        } else {
            u.setCode(request.getParameter("code"));
        }

        Boolean passwordCheckFlag = true;
        String password = request.getParameter("password");
        if(password == null || password.equals("") ) {
            passwordCheckFlag = false;
        } else {
            u.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            password,
                            (String)this.getServletContext().getAttribute("pepper")));
        }

        List<String> errors = UserValidator.validate(u, codeDuplicateCheckFlag, passwordCheckFlag);
        if(errors.size() > 0) {
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("errors", errors);
            request.setAttribute("user", u);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
            rd.forward(request, response);
        } else {

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "更新が完了しました。");

            request.getSession().removeAttribute("user_id");
            request.getSession().setAttribute("login_user", u);

            response.sendRedirect(request.getContextPath() + "/");

        }

        }
    }

}
