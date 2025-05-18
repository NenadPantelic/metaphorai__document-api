package ai.metaphor.document_api.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record DocumentResponse(String documentId,
                               String name,
                               String text,
                               String origin,
                               String path,
                               String type,
                               DocumentStatus documentStatus,
                               List<Metaphor> metaphors,
                               Instant createdAt,
                               Instant updatedAt) {
}
