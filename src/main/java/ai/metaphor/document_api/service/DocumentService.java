package ai.metaphor.document_api.service;

import ai.metaphor.document_api.dto.request.DocumentFilterRequest;
import ai.metaphor.document_api.dto.response.Document;
import ai.metaphor.document_api.dto.response.DocumentItem;

import java.util.List;

public interface DocumentService {

    List<DocumentItem> filterDocuments(DocumentFilterRequest documentFilterRequest);

    Document getDocument(String documentId);
}
