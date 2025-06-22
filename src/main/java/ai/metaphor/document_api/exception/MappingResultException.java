package ai.metaphor.document_api.exception;

public class MappingResultException extends RuntimeException {

    public MappingResultException(String message) {
        super(message);
    }

    public MappingResultException(String message, Throwable cause) {
        super(message, cause);
    }
}
