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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Dog;
import models.Report;
import models.ReportImage;
import models.User;
import models.validators.ReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/reports/create")
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
        ReportImage ri = new ReportImage();

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

        ri.setReport(r);

        Part part = request.getPart("image");
        int file_id = 0;

        InputStream inpStream = part.getInputStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        int c = 0;
        while((c = inpStream.read()) != -1) {
            outStream.write(c);
        }

        byte[] bytes = outStream.toByteArray();

        ri.setImage(bytes);


        List<String> errors = ReportValidator.validate(r);
        if(errors.size() > 0) {
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("report", r);
            request.setAttribute("reportImage", ri);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
            rd.forward(request, response);
        } else {
            em.getTransaction().begin();
            em.persist(r);
            em.persist(ri);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "登録が完了しました。");

            request.getSession().removeAttribute("dog");
            request.getSession().setAttribute("file_id", file_id);
            response.sendRedirect(request.getContextPath() + "/reports/index");
        }


    }

}
