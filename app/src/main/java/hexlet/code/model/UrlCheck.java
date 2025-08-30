package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UrlCheck {
    private Integer id;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    private Integer urlId;
    private LocalDateTime createdAt;

    public UrlCheck(Integer urlId) {
        this.urlId = urlId;
    }
}
