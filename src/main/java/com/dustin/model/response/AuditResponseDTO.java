package com.dustin.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditResponseDTO {
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("created_on")
    private Timestamp createdOn;
    @JsonProperty("modified_by")
    private String modifiedBy;
    @JsonProperty("modified_on")
    private Timestamp modifiedOn;
}
