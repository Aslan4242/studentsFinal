package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TermModifyingController", urlPatterns = "/term_modifying")
public class TermModifyingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTerm = req.getParameter("idModifyTerm");
        Term selectedTerm = DBManager.getTermById(idTerm);
        String duration = selectedTerm.getDuration();
        String durationNum = duration.substring(0,duration.indexOf(" "));
        req.setAttribute("selectedTermDuration", durationNum);
        req.setAttribute("selectedTerm", selectedTerm);
        ArrayList<Discipline> disciplines = DBManager.getAllActiveDisciplines();
        req.setAttribute("allDisciplines", disciplines);

        req.getRequestDispatcher("WEB-INF/JSP/term_modifying.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String modifiedDuration = req.getParameter("modifiedDuration");
        String[] modifiedDisciplinesId = req.getParameterValues("modifiedDisciplinesId");
        String id = req.getParameter("id");
       if(!modifiedDuration.equals("")) {
            DBManager.modifyTerm(id,modifiedDuration, modifiedDisciplinesId);
            resp.sendRedirect("/term");
        }
    }
}
