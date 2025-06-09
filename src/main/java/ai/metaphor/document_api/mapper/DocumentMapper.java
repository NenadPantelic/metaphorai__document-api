package ai.metaphor.document_api.mapper;

import ai.metaphor.document_api.dto.response.Document;
import ai.metaphor.document_api.dto.response.DocumentItem;
import ai.metaphor.document_api.dto.response.DocumentStatus;
import ai.metaphor.document_api.dto.response.Metaphor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentMapper {

    private static final String OBJECT_ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String TEXT_KEY = "text";
    private static final String STATUS_KEY = "status";
    private static final String PATH_KEY = "path";
    private static final String TYPE_KEY = "type";
    private static final String ORIGIN_KEY = "origin";

    public Document bsonDocumentToDocument(org.bson.Document document) {
        if (document == null) {
            return null;
        }

        List<Metaphor> metaphors = document.getList("metaphors", Metaphor.class);
        return Document.builder()
                .id(document.get(OBJECT_ID_KEY).toString())
                .status(DocumentStatus.valueOf(document.getString(STATUS_KEY)))
                .text(document.getString(TEXT_KEY))
                .name(document.getString(NAME_KEY))
                .path(document.getString(PATH_KEY))
                .type(document.getString(TYPE_KEY))
                .origin(document.getString(ORIGIN_KEY))
                .metaphors(metaphors)
                .build();
    }

    public DocumentItem bsonDocumentToDocumentItem(org.bson.Document document) {
        if (document == null) {
            return null;
        }

        return DocumentItem.builder()
                .id(document.get(OBJECT_ID_KEY).toString())
                .status(DocumentStatus.valueOf(document.getString(STATUS_KEY)))
                .name(document.getString(NAME_KEY))
                .origin(document.getString(ORIGIN_KEY))
                .build();
    }

    public List<DocumentItem> bsonDocumentsToDocumentItems(List<org.bson.Document> documents) {
        if (documents == null) {
            return null;
        }

        return documents.stream()
                .map(this::bsonDocumentToDocumentItem)
                .collect(Collectors.toList());
    }

}
