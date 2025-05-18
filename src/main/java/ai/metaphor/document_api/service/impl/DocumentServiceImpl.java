package ai.metaphor.document_api.service.impl;

import ai.metaphor.document_api.dto.request.DocumentFilterRequest;
import ai.metaphor.document_api.dto.response.DocumentItemResponse;
import ai.metaphor.document_api.dto.response.DocumentResponse;
import ai.metaphor.document_api.service.DocumentService;

import java.util.List;

public class DocumentServiceImpl implements DocumentService {

    @Override
    public List<DocumentItemResponse> filterDocuments(DocumentFilterRequest documentFilterRequest) {
        return null;
    }

    @Override
    public DocumentResponse getDocument(String documentId) {
        return null;
    }
}
