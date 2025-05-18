package ai.metaphor.document_api.service;

import ai.metaphor.document_api.dto.request.DocumentFilterRequest;
import ai.metaphor.document_api.dto.response.DocumentItemResponse;
import ai.metaphor.document_api.dto.response.DocumentResponse;

import java.util.List;

public interface DocumentService {

    List<DocumentItemResponse> filterDocuments(DocumentFilterRequest documentFilterRequest);

    DocumentResponse getDocument(String documentId);
}
