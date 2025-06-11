package ai.metaphor.document_api.api.error;

public record ApiError(String message,
                       int status) {
}
