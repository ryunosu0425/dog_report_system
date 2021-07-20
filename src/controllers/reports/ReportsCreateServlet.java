package controllers.reports;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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
import models.Report;
import models.User;
import models.validators.ReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/reports/create")
@MultipartConfig(maxFileSize = 10485760)

public class ReportsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = new Report();

        r.setUser((User)request.getSession().getAttribute("login_user"));
        r.setDog((Dog)request.getSession().getAttribute("dog"));
        r.setContent(request.getParameter("content"));
        r.setTitle(request.getParameter("title"));

        Date report_date = new Date(System.currentTimeMillis());
        String rd_str = request.getParameter("report_date");
        if(rd_str != null && !rd_str.equals("")) {
            report_date = Date.valueOf(request.getParameter("report_date"));
        }
        r.setReport_date(report_date);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        r.setCreated_at(currentTime);
        r.setUpdated_at(currentTime);

        Part part = request.getPart("image1");

        int file_id = 0;

        InputStream inpStream = part.getInputStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        int c = 0;
        while((c = inpStream.read()) != -1) {
            outStream.write(c);
        }

        byte[] bytes = outStream.toByteArray();

        r.setImage1(bytes);

        Part part2 = request.getPart("image2");

        int file_id2 = 0;

        InputStream inpStream2 = part2.getInputStream();
        ByteArrayOutputStream outStream2 = new ByteArrayOutputStream();

        int b = 0;
        while((b = inpStream2.read()) != -1) {
            outStream2.write(b);
        }

        byte[] bytes2 = outStream2.toByteArray();

        r.setImage2(bytes2);

        List<String> errors = ReportValidator.validate(r);
        if(errors.size() > 0) {
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("report", r);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
            rd.forward(request, response);
        } else {
            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();

            file_id = r.getId();
            file_id2 = r.getId();

            em.close();
            request.getSession().setAttribute("flush", "登録が完了しました。");

            request.getSession().removeAttribute("dog");
            request.getSession().setAttribute("file_id", file_id);
            request.getSession().setAttribute("file_id2", file_id2);
            response.sendRedirect(request.getContextPath() + "/reports/index");
        }


    }

}
