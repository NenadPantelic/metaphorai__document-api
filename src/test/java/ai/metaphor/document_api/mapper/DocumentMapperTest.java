package ai.metaphor.document_api.mapper;

import ai.metaphor.document_api.dto.response.DocumentItem;
import ai.metaphor.document_api.dto.response.DocumentStatus;
import ai.metaphor.document_api.dto.response.Metaphor;
import ai.metaphor.document_api.dto.response.MetaphorType;
import ai.metaphor.document_api.exception.MappingResultException;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DocumentMapperTest {

    private static final List<Document> DOCUMENTS = new ArrayList<>();

    private static final Document DOCUMENT_ONE__WITH_ALL_FIELDS;
    private static final Document DOCUMENT_TWO__WITHOUT_METAPHORS;
    private static final Document DOCUMENT_THREE__WITH_INVALID_DOC_STATUS;
    private static final Document DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE;
    private static final Document METAPHOR_ONE;
    private static final Document METAPHOR_FOUR_WITH_INVALID_TYPE;


    private final DocumentMapper documentMapper = new DocumentMapper();

    static {
        /// document 1 ///
        String testOid1 = "test-oid-1";
        String testDocStatus1 = DocumentStatus.DONE.name();
        String testText1 = "test-text-1";
        String testName1 = "test-name-1";
        String testPath1 = "test-path-1";
        String testType1 = "test-type-1";
        String testOrigin1 = "test-origin-1";

        DOCUMENT_ONE__WITH_ALL_FIELDS = new Document();
        DOCUMENT_ONE__WITH_ALL_FIELDS.put(DocumentMapper.OBJECT_ID_KEY, testOid1);
        DOCUMENT_ONE__WITH_ALL_FIELDS.put(DocumentMapper.STATUS_KEY, testDocStatus1);
        DOCUMENT_ONE__WITH_ALL_FIELDS.put(DocumentMapper.TEXT_KEY, testText1);
        DOCUMENT_ONE__WITH_ALL_FIELDS.put(DocumentMapper.NAME_KEY, testName1);
        DOCUMENT_ONE__WITH_ALL_FIELDS.put(DocumentMapper.PATH_KEY, testPath1);
        DOCUMENT_ONE__WITH_ALL_FIELDS.put(DocumentMapper.TYPE_KEY, testType1);
        DOCUMENT_ONE__WITH_ALL_FIELDS.put(DocumentMapper.ORIGIN_KEY, testOrigin1);

        int testOffset1 = 3;
        String testPhrase1 = "test-phrase";
        String testMetaphorType1 = MetaphorType.DIRECT.name();
        String testExplanation1 = "test-explanation";

        METAPHOR_ONE = new Document();
        METAPHOR_ONE.put(DocumentMapper.OFFSET_KEY, testOffset1);
        METAPHOR_ONE.put(DocumentMapper.PHRASE_KEY, testPhrase1);
        METAPHOR_ONE.put(DocumentMapper.METAPHOR_TYPE_KEY, testMetaphorType1);
        METAPHOR_ONE.put(DocumentMapper.EXPLANATION_KEY, testExplanation1);
        DOCUMENT_ONE__WITH_ALL_FIELDS.put(DocumentMapper.METAPHORS_KEY, List.of(METAPHOR_ONE));

        DOCUMENTS.add(DOCUMENT_ONE__WITH_ALL_FIELDS);

        /// document 2 - without metaphors ///
        String testOid2 = "test-oid-2";
        String testDocStatus2 = DocumentStatus.PENDING.name();
        String testText2 = "test-text-2";
        String testName2 = "test-name-2";
        String testPath2 = "test-path-2";
        String testType2 = "test-type-2";
        String testOrigin2 = "test-origin-2";

        DOCUMENT_TWO__WITHOUT_METAPHORS = new Document();
        DOCUMENT_TWO__WITHOUT_METAPHORS.put(DocumentMapper.OBJECT_ID_KEY, testOid2);
        DOCUMENT_TWO__WITHOUT_METAPHORS.put(DocumentMapper.STATUS_KEY, testDocStatus2);
        DOCUMENT_TWO__WITHOUT_METAPHORS.put(DocumentMapper.TEXT_KEY, testText2);
        DOCUMENT_TWO__WITHOUT_METAPHORS.put(DocumentMapper.NAME_KEY, testName2);
        DOCUMENT_TWO__WITHOUT_METAPHORS.put(DocumentMapper.PATH_KEY, testPath2);
        DOCUMENT_TWO__WITHOUT_METAPHORS.put(DocumentMapper.TYPE_KEY, testType2);
        DOCUMENT_TWO__WITHOUT_METAPHORS.put(DocumentMapper.ORIGIN_KEY, testOrigin2);

        DOCUMENTS.add(DOCUMENT_TWO__WITHOUT_METAPHORS);

        /// document 3 - invalid document status ///
        String testOid3 = "test-oid-3";
        String testDocStatus3 = "invalid-status";
        String testText3 = "test-text-3";
        String testName3 = "test-name-3";
        String testPath3 = "test-path-3";
        String testType3 = "test-type-3";
        String testOrigin3 = "test-origin-3";

        DOCUMENT_THREE__WITH_INVALID_DOC_STATUS = new Document();
        DOCUMENT_THREE__WITH_INVALID_DOC_STATUS.put(DocumentMapper.OBJECT_ID_KEY, testOid3);
        DOCUMENT_THREE__WITH_INVALID_DOC_STATUS.put(DocumentMapper.STATUS_KEY, testDocStatus3);
        DOCUMENT_THREE__WITH_INVALID_DOC_STATUS.put(DocumentMapper.TEXT_KEY, testText3);
        DOCUMENT_THREE__WITH_INVALID_DOC_STATUS.put(DocumentMapper.NAME_KEY, testName3);
        DOCUMENT_THREE__WITH_INVALID_DOC_STATUS.put(DocumentMapper.PATH_KEY, testPath3);
        DOCUMENT_THREE__WITH_INVALID_DOC_STATUS.put(DocumentMapper.TYPE_KEY, testType3);
        DOCUMENT_THREE__WITH_INVALID_DOC_STATUS.put(DocumentMapper.ORIGIN_KEY, testOrigin3);

        /// document 4 - invalid metaphor type ///
        String testOid4 = "test-oid-4";
        String testDocStatus4 = DocumentStatus.INCOMPLETE.name();
        String testText4 = "test-text-3";
        String testName4 = "test-name-3";
        String testPath4 = "test-path-3";
        String testType4 = "test-type-3";
        String testOrigin4 = "test-origin-3";

        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE = new Document();
        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE.put(DocumentMapper.OBJECT_ID_KEY, testOid4);
        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE.put(DocumentMapper.STATUS_KEY, testDocStatus4);
        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE.put(DocumentMapper.TEXT_KEY, testText4);
        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE.put(DocumentMapper.NAME_KEY, testName4);
        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE.put(DocumentMapper.PATH_KEY, testPath4);
        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE.put(DocumentMapper.TYPE_KEY, testType4);
        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE.put(DocumentMapper.ORIGIN_KEY, testOrigin4);

        int testOffsetFour = 5;
        String testPhraseFOur = "test-phrase-4";
        String testMetaphorTypeFour = "invalid-metaphor-type";
        String testExplanationFour = "test-explanation-4";
        METAPHOR_FOUR_WITH_INVALID_TYPE = new Document();
        METAPHOR_FOUR_WITH_INVALID_TYPE.put(DocumentMapper.OFFSET_KEY, testOffsetFour);
        METAPHOR_FOUR_WITH_INVALID_TYPE.put(DocumentMapper.PHRASE_KEY, testPhraseFOur);
        METAPHOR_FOUR_WITH_INVALID_TYPE.put(DocumentMapper.METAPHOR_TYPE_KEY, testMetaphorTypeFour);
        METAPHOR_FOUR_WITH_INVALID_TYPE.put(DocumentMapper.EXPLANATION_KEY, testExplanationFour);
        DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE.put(DocumentMapper.METAPHORS_KEY, List.of(METAPHOR_FOUR_WITH_INVALID_TYPE));
    }

    @Test
    public void testBsonDocumentToDocumentItemWithNullDocument() {
        Assertions.assertThat(documentMapper.bsonDocumentToDocumentItem(null)).isNull();
    }

    @Test
    public void testBsonDocumentToDocumentItem() {
        List<DocumentItem> documentItems = documentMapper.bsonDocumentsToDocumentItems(DOCUMENTS);
        Assertions.assertThat(documentItems.size()).isEqualTo(2);

        /// document one ///
        DocumentItem documentItemOne = documentItems.get(0);
        Assertions.assertThat(documentItemOne.id()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.OBJECT_ID_KEY)
        );
        Assertions.assertThat(documentItemOne.name()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.NAME_KEY)
        );
        Assertions.assertThat(documentItemOne.origin()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.ORIGIN_KEY)
        );
        Assertions.assertThat(documentItemOne.status().name()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.STATUS_KEY)
        );

        /// document two ///
        DocumentItem documentItemTwo = documentItems.get(1);
        Assertions.assertThat(documentItemTwo.id()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.OBJECT_ID_KEY)
        );
        Assertions.assertThat(documentItemTwo.name()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.NAME_KEY)
        );
        Assertions.assertThat(documentItemTwo.origin()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.ORIGIN_KEY)
        );
        Assertions.assertThat(documentItemTwo.status().name()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.STATUS_KEY)
        );
    }

    @Test
    public void testBsonDocumentToDocumentItemWhenDocumentHasInvalidStatus() {
        Assertions.assertThatThrownBy(
                () -> documentMapper.bsonDocumentToDocumentItem(DOCUMENT_THREE__WITH_INVALID_DOC_STATUS)
        ).isInstanceOf(MappingResultException.class);
    }

    @Test
    public void testBsonDocumentToDocumentWithNullDocument() {
        Assertions.assertThat(documentMapper.bsonDocumentToDocument(null)).isNull();
    }

    @Test
    public void testBsonDocumentToDocument() {
        ai.metaphor.document_api.dto.response.Document document = documentMapper.bsonDocumentToDocument(DOCUMENT_ONE__WITH_ALL_FIELDS);

        Assertions.assertThat(document.id()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.OBJECT_ID_KEY)
        );
        Assertions.assertThat(document.text()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.TEXT_KEY)
        );
        Assertions.assertThat(document.name()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.NAME_KEY)
        );
        Assertions.assertThat(document.path()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.PATH_KEY)
        );
        Assertions.assertThat(document.type()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.TYPE_KEY)
        );
        Assertions.assertThat(document.origin()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.ORIGIN_KEY)
        );
        Assertions.assertThat(document.status().name()).isEqualTo(
                DOCUMENT_ONE__WITH_ALL_FIELDS.getString(DocumentMapper.STATUS_KEY)
        );
        Assertions.assertThat(document.metaphors()).isNotNull();

        List<Metaphor> metaphors = document.metaphors();
        Assertions.assertThat(metaphors).isNotNull();
        Assertions.assertThat(metaphors.size()).isEqualTo(1);
        Metaphor metaphor = metaphors.get(0);

        System.out.println(metaphor);
        Assertions.assertThat(metaphor.explanation()).isEqualTo(METAPHOR_ONE.getString(DocumentMapper.EXPLANATION_KEY));
        Assertions.assertThat(metaphor.phrase()).isEqualTo(METAPHOR_ONE.getString(DocumentMapper.PHRASE_KEY));
        Assertions.assertThat(metaphor.offset()).isEqualTo(METAPHOR_ONE.getInteger(DocumentMapper.OFFSET_KEY));
        Assertions.assertThat(metaphor.type().name()).isEqualTo(METAPHOR_ONE.getString(DocumentMapper.METAPHOR_TYPE_KEY));
        Assertions.assertThat(metaphor.length()).isEqualTo(metaphor.phrase().length());
    }

    @Test
    public void testBsonDocumentToDocumentWhenDocumentDoesNotHaveMetaphor() {
        ai.metaphor.document_api.dto.response.Document document = documentMapper.bsonDocumentToDocument(DOCUMENT_TWO__WITHOUT_METAPHORS);

        Assertions.assertThat(document.id()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.OBJECT_ID_KEY)
        );
        Assertions.assertThat(document.text()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.TEXT_KEY)
        );
        Assertions.assertThat(document.name()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.NAME_KEY)
        );
        Assertions.assertThat(document.path()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.PATH_KEY)
        );
        Assertions.assertThat(document.type()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.TYPE_KEY)
        );
        Assertions.assertThat(document.origin()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.ORIGIN_KEY)
        );
        Assertions.assertThat(document.status().name()).isEqualTo(
                DOCUMENT_TWO__WITHOUT_METAPHORS.getString(DocumentMapper.STATUS_KEY)
        );
        Assertions.assertThat(document.metaphors()).isEmpty();
    }

    @Test
    public void testBsonDocumentToDocumentWhenDocumentHasInvalidStatus() {
        Assertions.assertThatThrownBy(
                () -> documentMapper.bsonDocumentToDocument(DOCUMENT_THREE__WITH_INVALID_DOC_STATUS)
        ).isInstanceOf(MappingResultException.class);
    }

    @Test
    public void testBsonDocumentToDocumentWhenMetaphorHasInvalidStatus() {
        Assertions.assertThatThrownBy(
                () -> documentMapper.bsonDocumentToDocument(DOCUMENT_FOUR__WITH_INVALID_METAPHOR_TYPE)
        ).isInstanceOf(MappingResultException.class);
    }
}