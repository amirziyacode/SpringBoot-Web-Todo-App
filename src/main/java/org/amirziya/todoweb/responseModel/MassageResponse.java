package org.amirziya.todoweb.responseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class MassageResponse {


    @JsonProperty("Status")
    private String status;
    @JsonProperty("Error_Massage")
    private String message;

    public MassageResponse(String status, String message) {
        this.status= status;
        this.message = message;
    }
}
