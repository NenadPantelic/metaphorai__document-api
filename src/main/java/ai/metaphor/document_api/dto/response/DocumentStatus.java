package ai.metaphor.document_api.dto.response;

public enum DocumentStatus {

    // not yet processed, waiting for its turn
    PENDING,
    // being processed at the moment
    PROCESSING,
    // waiting reprocessing
    PENDING_REPROCESSING,
    // being reprocessed at the moment
    REPROCESSING,
    // processing has been done, but some chunks have not been processed (error)
    INCOMPLETE,
    // completely processed
    DONE
}
