package ai.metaphor.document_api.mapper;

import ai.metaphor.document_api.dto.response.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentMapper {

    private static final String OBJECT_ID_KEY = "_id";
    private static final String NAME_KEY = "name";
    private static final String TEXT_KEY = "text";
    private static final String STATUS_KEY = "status";
    private static final String PATH_KEY = "path";
    private static final String TYPE_KEY = "type";
    private static final String ORIGIN_KEY = "origin";

    private static final String OFFSET = "offset";
    private static final String PHRASE = "phrase";
    private static final String TYPE = "type";
    private static final String EXPLANATION = "explanation";


    // todo: add safe guards against missing/faulty fields
    public Document bsonDocumentToDocument(org.bson.Document document) {
        if (document == null) {
            return null;
        }

        List<org.bson.Document> metaphors = (List<org.bson.Document>) document.get("metaphors");
        return Document.builder()
                .id(document.get(OBJECT_ID_KEY).toString())
                .status(DocumentStatus.valueOf(document.getString(STATUS_KEY)))
                .text(document.getString(TEXT_KEY))
                .name(document.getString(NAME_KEY))
                .path(document.getString(PATH_KEY))
                .type(document.getString(TYPE_KEY))
                .origin(document.getString(ORIGIN_KEY))
                .metaphors(metaphorDocumentsToMetaphors(metaphors))
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

    private List<Metaphor> metaphorDocumentsToMetaphors(List<org.bson.Document> metaphors) {
        return metaphors.stream()
                .map(this::metaphorDocumentToMetaphor)
                .sorted(Comparator.comparingInt(Metaphor::offset))
                .collect(Collectors.toList());
    }

    private Metaphor metaphorDocumentToMetaphor(org.bson.Document document) {
        String phrase = document.getString(PHRASE);
        return Metaphor.builder()
                .offset(document.getInteger(OFFSET))
                .explanation(document.getString(EXPLANATION))
                .phrase(phrase)
                .type(MetaphorType.valueOf(document.getString(TYPE)))
                .length(phrase.length())
                .build();
    }
}
