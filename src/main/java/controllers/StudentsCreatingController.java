package controllers;
import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "StudentsCreatingController", urlPatterns = "/students_creating")

public class StudentsCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/JSP/students_creating.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String group = req.getParameter("group");
        String date = req.getParameter("date");
        if (!lastName.equals("") || !firstName.equals("")|| !group.equals("") || !date.equals("")){
            String pattern = "MM/dd/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date2 = null;
            try {
                date2 = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String pattern1 = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
            String dateFormat = simpleDateFormat1.format(date2);

            DBManager.createStudent(lastName,firstName,group,dateFormat);
            resp.sendRedirect("/students");
        }
    }
}
