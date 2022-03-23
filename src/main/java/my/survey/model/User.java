package my.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "^[0-9]+$", message = "Некорректный идентификатор пользователя")
    @Column(nullable = false, unique = true)
    private String externalId;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyResult> results;

    public static User of(String externalId) {
        User user = new User();
        user.setExternalId(externalId);
        user.results = new ArrayList<>();
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<SurveyResult> getResults() {
        return results;
    }

    public void setResults(List<SurveyResult> results) {
        this.results = results;
    }

    public void addResult(SurveyResult result) {
        results.add(result);
        result.setUser(this);
    }

    public void deleteResult(SurveyResult result) {
        results.remove(result);
        result.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", externalId='" + externalId + '\''
                + ", results=" + results
                + '}';
    }
}
