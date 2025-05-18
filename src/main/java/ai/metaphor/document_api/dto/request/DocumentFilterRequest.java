package ai.metaphor.document_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DocumentFilterRequest(@NotBlank String filter,
                                    String sortBy,
                                    String sortOrder,
                                    int limit) {
}
