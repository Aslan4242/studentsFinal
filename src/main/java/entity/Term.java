package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Term {
    private int id;
    private String term;
    private String duration;
    private int status;
    private ArrayList<Discipline> disciplines = new ArrayList<>();


    public Term(int id, String term, String duration, int status) {
        this.id = id;
        this.term = term;
        this.duration = duration;
        this.status = status;
    }

    public Term() {
    }
    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
    public void addDiscipline(Discipline discipline){
        disciplines.add(discipline);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return id == term.id &&
                status == term.status &&
                Objects.equals(term, term.term) &&
                Objects.equals(duration, term.duration) &&
                Objects.equals(disciplines, term.disciplines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, term, duration, status, disciplines);

    }

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", term='" + term + '\'' +
                ", duration='" + duration + '\'' +
                ", status=" + status +
                ", disciplines=" + disciplines +
                '}';
    }

}
