package org.labmonkeys.home_library.catalog.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class AuthorDTO {
    String openLibraryUrl;
    String name;
}