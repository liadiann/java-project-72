package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Url {
    private Long id;
    private String name;
    private LocalDateTime created_at;

    public Url(String name, LocalDateTime created_at) {
        this.name = name;
        this.created_at = created_at;
    }
}
