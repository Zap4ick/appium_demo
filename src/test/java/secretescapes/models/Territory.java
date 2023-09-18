package secretescapes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Territory {
    GB("United Kingdom","en"), DE("Deutschland","de"), IT("Italia","it");

    private String fullName;
    private String language;
}
