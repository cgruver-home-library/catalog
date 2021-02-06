package org.labmonkeys.home_library.catalog.colaborators.open_library.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@RegisterForReflection
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublisherOL {

    String name;

}