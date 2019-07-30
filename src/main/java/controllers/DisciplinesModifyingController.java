package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisciplinesModifyingController", urlPatterns = "/disciplines_modifying")
public class DisciplinesModifyingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id.equals("")) {
            resp.sendRedirect("/disciplines");
        } else {
            req.setAttribute("id",id);
            String disciplineName = DBManager.getDisciplineName(id);
            req.setAttribute("disciplineName", disciplineName);
            req.getRequestDispatcher("WEB-INF/JSP/disciplines_modifying.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String modifiedDiscipline = req.getParameter("modifiedDiscipline");
        if(!modifiedDiscipline.equals("")){
            DBManager.modifyDiscipline(id, modifiedDiscipline);
            resp.sendRedirect("/disciplines");
        }
    }
}
