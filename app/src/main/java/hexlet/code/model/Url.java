package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Url {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;

    public Url(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public Url(String name) {
        this.name = name;
    }
}
