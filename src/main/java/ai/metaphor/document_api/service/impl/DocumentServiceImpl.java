package ai.metaphor.document_api.service.impl;

import ai.metaphor.document_api.dto.request.DocumentFilterRequest;
import ai.metaphor.document_api.dto.response.Document;
import ai.metaphor.document_api.dto.response.DocumentItem;
import ai.metaphor.document_api.mapper.DocumentMapper;
import ai.metaphor.document_api.repository.DocumentRepository;
import ai.metaphor.document_api.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_LIMIT = 50;

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Override
    public List<DocumentItem> filterDocuments(DocumentFilterRequest documentFilterRequest) {
        log.info("Filtering documents by {}", documentFilterRequest);
        List<org.bson.Document> documents = documentRepository.filter(
                documentFilterRequest.filter(),
                documentFilterRequest.sortBy(),
                documentFilterRequest.sortOrder(),
                documentFilterRequest.page() != null ? documentFilterRequest.page() : DEFAULT_PAGE,
                documentFilterRequest.limit() != null ? documentFilterRequest.limit() : DEFAULT_LIMIT
        );
        return documentMapper.bsonDocumentsToDocumentItems(documents);
    }

    @Override
    public Document getDocument(String documentId) {
        log.info("Get document by id {}", documentId);
        return documentMapper.bsonDocumentToDocument(documentRepository.findById(documentId));
    }
}
