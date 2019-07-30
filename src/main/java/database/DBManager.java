package database;

import entity.Account;
import entity.Discipline;
import entity.Student;
import entity.Term;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBManager {
    public static ArrayList<Student> getAllActiveStudents() {
        ArrayList<Student> allStudent = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from students_control.student where status = '1'");

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setLastName(rs.getString("surname"));
                student.setFirstName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);
                allStudent.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allStudent;
    }

    public static ArrayList<Discipline> getAllActiveDisciplines() {
        ArrayList<Discipline> allDisciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from disciplines where status = '1'");

            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                allDisciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allDisciplines;
    }

    public static ArrayList<Term> getAllActiveTerms() {
        ArrayList<Term> allActiveTerms = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM term where status = '1'");

            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setTerm(rs.getString("term"));
                term.setDuration(rs.getString("duration"));
                term.setStatus(1);
                allActiveTerms.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allActiveTerms;
    }


    public static ArrayList<Discipline> getAllActiveDisciplinesByTerms(int idTerm) {
        ArrayList<Discipline> allDisciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select disciplines.id, term.term, disciplines.discipline from term\n" +
                    "join term_discipline on term.id=term_discipline.id_term\n" +
                    "join disciplines on term_discipline.id_discipline=disciplines.id\n" +
                    "where term.id=" + idTerm + " and disciplines.status ='1'");

            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("disciplines.id"));
                discipline.setDiscipline(rs.getString("disciplines.discipline"));
                discipline.setStatus(1);
                allDisciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allDisciplines;
    }


    public static void createStudent(String lastName, String firstName, String group, String date) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO `student` (`surname`, `name`, `group`, `date`) VALUES ('" + lastName + "', '" + firstName + "', '" + group + "', '" + date + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDiscipline(String disciplineName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO `disciplines` (`discipline`) VALUES ('" + disciplineName + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTerm(String duration, String[] disciplines) {
        String durationNoSpace = duration.trim();
        int durationInt = Integer.parseInt(durationNoSpace);
        String wordWeek = null;
        if ((durationInt - 1) % 10 == 0 && durationInt != 11) {
            wordWeek = " неделя";
        } else if ((durationNoSpace.substring(durationNoSpace.length() - 1).equals("2") ||
                durationNoSpace.substring(durationNoSpace.length() - 1).equals("3") ||
                durationNoSpace.substring(durationNoSpace.length() - 1).equals("4")) &&
                durationInt != 12 && durationInt != 13 && durationInt != 14) {
            wordWeek = " недели";
        } else {
            wordWeek = " недель";
        }
        String durationResult = durationInt + wordWeek;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,  term FROM term ORDER BY ID DESC limit 1");
            String currentTerm = null;
            int id = 0;
            while (resultSet.next()) {
                currentTerm = resultSet.getString("term");
                id = resultSet.getInt("id");
            }
            int termNumber = 0;
            termNumber = Integer.parseInt(currentTerm.substring(7));
            String term = "Семестр" + (++termNumber);
            statement.execute("INSERT INTO `term` (`term`, `duration`) VALUES ('" + term + "','" + durationResult + "')");

            int newId = ++id;
            if (disciplines != null) {
                for (String discipline : disciplines) {
                    ResultSet resultSet1 = statement.executeQuery("SELECT id FROM disciplines\n" +
                            "where discipline ='" + discipline + "'");
                    int disciplineId = 0;
                    while (resultSet1.next()) {
                        disciplineId = resultSet1.getInt("id");
                    }
                    statement.execute("INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('" + newId + "', '" + disciplineId + "')");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteDiscipline(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            statement.execute("UPDATE `disciplines` SET `status` = '0' WHERE (`id` = '" + id + "')");
            //UPDATE `students_control`.`disciplines` SET `status` = '0' WHERE (`id` = '11');

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDisciplineName(String id) {
        String name = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select discipline from disciplines where id =" + id + "");
            //select discipline from disciplines where id =11;

            while (rs.next()) {
                name = rs.getString("discipline");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;

    }


    public static void modifyDiscipline(String id, String modifiedDiscipline) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            statement.execute("UPDATE `disciplines` SET `discipline` = '" + modifiedDiscipline + "' WHERE (`id` = '" + id + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            statement.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = '" + id + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyStudent(String id, String modifiedLastName, String modifiedFirstName, String modifiedGroup, String modifiedDate) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            statement.execute("UPDATE `student` SET `surname` = '" + modifiedLastName + "', `name` = '" + modifiedFirstName + "', `group` = '" + modifiedGroup + "', `date` = '" + modifiedDate + "' WHERE (`id` = '" + id + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteTerm(String idToDelete) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            statement.execute("UPDATE `term` SET `status` = '0' WHERE (`id` = '" + idToDelete + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Term getTermById(String idTerm) {
        String name = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from term where id =" + idTerm + "");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setDuration(rs.getString("duration"));
                term.setTerm(rs.getString("term"));
                term.setStatus(1);
                return term;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void modifyTerm(String id, String modifiedDuration, String[] modifiedDisciplinesId) {
        String durationNoSpace = modifiedDuration.trim();
        int durationInt = Integer.parseInt(durationNoSpace);
        String wordWeek = null;
        if ((durationInt - 1) % 10 == 0 && durationInt != 11) {
            wordWeek = " неделя";
        } else if ((durationNoSpace.substring(durationNoSpace.length() - 1).equals("2") ||
                durationNoSpace.substring(durationNoSpace.length() - 1).equals("3") ||
                durationNoSpace.substring(durationNoSpace.length() - 1).equals("4")) &&
                durationInt != 12 && durationInt != 13 && durationInt != 14) {
            wordWeek = " недели";
        } else {
            wordWeek = " недель";
        }
        String durationResult = durationInt + wordWeek;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            statement.execute("DELETE FROM `term_discipline` WHERE (`id_term` = '" + id + "')");
            if (modifiedDisciplinesId != null) {
                for (String newDisciplineId : modifiedDisciplinesId) {
                    statement.execute("INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('" + id + "', '" + newDisciplineId + "')");
                }
            }

            statement.execute("UPDATE `term` SET `duration` = '" + durationResult + "' WHERE (`id` = '" + id + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Term> getStudentsTerm(String id) {
        ArrayList<Term> studentsTerm = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select distinct term_discipline.id_term, term.term from mark\n" +
                    "join term_discipline on mark.id_term_discipline=term_discipline.id\n" +
                    "join term on term_discipline.id_term = term.id\n" +
                    " where mark.id_student =" + id + "");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id_term"));
                term.setTerm(rs.getString("term"));
                term.setStatus(1);
                studentsTerm.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentsTerm;
    }

    public static LinkedHashMap<Discipline, String> getDisciplinesAndMarkByTerm(String id, String termId) {
        LinkedHashMap<Discipline, String> disciplinesAndMark = new LinkedHashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select disciplines.id, disciplines.discipline , mark.mark  from mark\n" +
                    "join term_discipline on mark.id_term_discipline=term_discipline.id\n" +
                    "join term on term_discipline.id_term = term.id\n" +
                    "join disciplines on term_discipline.id_discipline=disciplines.id\n" +
                    " where mark.id_student =" + id + " and term_discipline.id_term=" + termId + "");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                String mark = rs.getString("mark");
                disciplinesAndMark.put(discipline, mark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return disciplinesAndMark;

    }

    public static String getAvgMark(String id, String termId) {
        double result = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select  round(avg(mark.mark),1)   from mark\n" +
                    "join term_discipline on mark.id_term_discipline=term_discipline.id\n" +
                    "join term on term_discipline.id_term = term.id\n" +
                    "join disciplines on term_discipline.id_discipline=disciplines.id\n" +
                    " where mark.id_student =" + id + " and term_discipline.id_term=" + termId + "");
            while (rs.next()) {
                result = rs.getDouble("round(avg(mark.mark),1)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = " балла.";
        if (result == (int) result) {
            if (result == 1) {
                return (int) result + " балл.";
            }
            if (result == 5) {
                return (int) result + " баллов.";
            } else {
                return (int) result + s;
            }
        } else {
            return result + s;
        }
    }

    public static Account getAccountById(String login, String password) {
        String name = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/students_control?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, "root", "root");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from account where login ='" + login + "' and password = '" + password + "'");
            while (rs.next()) {
                Account account = new Account();
                account.setLogin(rs.getString("login"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
