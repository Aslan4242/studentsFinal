package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Student;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@WebServlet(name = "StudentProgressController", urlPatterns = "/student_progress")
public class StudentProgressController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idS");
        req.setAttribute("idS", id);
        ArrayList<Student> students = DBManager.getAllActiveStudents();
        for (Student student : students) {
            if (student.getId() == Integer.parseInt(id)) {
                req.setAttribute("selectedStudent", student);
                break;
            }
        }
        ArrayList<Term> studentsTerm = DBManager.getStudentsTerm(id);
        if (studentsTerm.size() == 0) {
            req.getRequestDispatcher("WEB-INF/JSP/student_progress.jsp").forward(req, resp);
        }
        String termId = req.getParameter("term_select");
        if (termId!=null){
            ArrayList<Term> terms = DBManager.getAllActiveTerms();
            req.setAttribute("firstTerm", studentsTerm.get(0));
            req.setAttribute("studentsTerm", studentsTerm);
            for (Term t : terms) {
                if (t.getId() == Integer.parseInt(termId)) {
                    req.setAttribute("allTerms", terms);
                    req.setAttribute("firstTerm", t);
                    LinkedHashMap<Discipline, String> disciplinesAndMarkByTerm = DBManager.getDisciplinesAndMarkByTerm(id, termId);
                    req.setAttribute("disciplinesAndMarkByTerm", disciplinesAndMarkByTerm);
                    String avgMark = DBManager.getAvgMark(id, termId);
                    req.setAttribute("avgMark", avgMark);
                    req.getRequestDispatcher("WEB-INF/JSP/student_progress.jsp").forward(req, resp);
                }
            }
        }

        req.setAttribute("firstTerm", studentsTerm.get(0));
        req.setAttribute("studentsTerm", studentsTerm);
        LinkedHashMap<Discipline, String> disciplinesAndMarkByTerm = DBManager.getDisciplinesAndMarkByTerm(id, String.valueOf(studentsTerm.get(0).getId()));
        req.setAttribute("disciplinesAndMarkByTerm", disciplinesAndMarkByTerm);
        String avgMark = DBManager.getAvgMark(id, String.valueOf(studentsTerm.get(0).getId()));
        req.setAttribute("avgMark", avgMark);
        req.getRequestDispatcher("WEB-INF/JSP/student_progress.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
