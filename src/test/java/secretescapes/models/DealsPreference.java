package secretescapes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DealsPreference {
    BASIC("basic_preference"), RECOMMENDED("recommended_preference"), REJECT("rejectNotifications");

    @Getter
    private String id;

    @Override
    public String toString(){
        return name().toLowerCase();
    }
}
