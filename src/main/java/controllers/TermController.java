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
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "TermController", urlPatterns = "/term")
public class TermController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Term> terms = DBManager.getAllActiveTerms();
        req.setAttribute("allTerms", terms);
        req.setAttribute("selectedTerm",terms.get(0));
        ArrayList<Discipline> disciplinesByTerm = DBManager.getAllActiveDisciplinesByTerms(terms.get(0).getId());
        req.setAttribute("allDisciplinesByTerm", disciplinesByTerm);

        req.getRequestDispatcher("WEB-INF/JSP/term.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idToDelete =req.getParameter("idToDelete");
        if (idToDelete!=null){
            DBManager.deleteTerm(idToDelete); // метод для удаления семестра
            resp.sendRedirect("/term");
        }else{
            ArrayList<Term> terms = DBManager.getAllActiveTerms();
            String termId = req.getParameter("term_select");
            int selectedId = Integer.parseInt(termId);
            for (Term s: terms ) {
                if (s.getId()==selectedId){
                    req.setAttribute("allTerms", terms);
                    req.setAttribute("selectedTerm",s);
                    ArrayList<Discipline> disciplinesByTerm = DBManager.getAllActiveDisciplinesByTerms(selectedId);
                    req.setAttribute("allDisciplinesByTerm", disciplinesByTerm);
                    req.getRequestDispatcher("WEB-INF/JSP/term.jsp").forward(req, resp);
                }
            }
        }


    }
}
