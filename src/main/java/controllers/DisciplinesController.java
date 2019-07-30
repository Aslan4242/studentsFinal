package controllers;

import database.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DisciplinesController", urlPatterns = "/disciplines")
public class DisciplinesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Discipline> disciplines = DBManager.getAllActiveDisciplines();
        req.setAttribute("allDisciplines",disciplines);
        req.getRequestDispatcher("WEB-INF/JSP/disciplines.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids =req.getParameter("ids");
        String[] idsmassiv = ids.split(",");
        for (String id:idsmassiv ) {
            DBManager.deleteDiscipline(id);
        }
        resp.sendRedirect("/disciplines");
    }
}
