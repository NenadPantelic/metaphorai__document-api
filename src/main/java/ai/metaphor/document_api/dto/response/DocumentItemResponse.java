package ai.metaphor.document_api.dto.response;

import lombok.Builder;

@Builder
public record DocumentItemResponse(String documentId,
                                   String name,
                                   String origin) {
}
