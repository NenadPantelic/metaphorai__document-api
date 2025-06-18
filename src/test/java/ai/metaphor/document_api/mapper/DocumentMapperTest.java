package ai.metaphor.document_api.mapper;

import ai.metaphor.document_api.dto.response.DocumentItem;
import ai.metaphor.document_api.dto.response.DocumentStatus;
import ai.metaphor.document_api.dto.response.MetaphorType;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DocumentMapperTest {

    private static final List<Document> DOCUMENTS = new ArrayList<>();

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

        Document documentOne = new Document();
        documentOne.put(DocumentMapper.OBJECT_ID_KEY, testOid1);
        documentOne.put(DocumentMapper.STATUS_KEY, testDocStatus1);
        documentOne.put(DocumentMapper.TEXT_KEY, testText1);
        documentOne.put(DocumentMapper.NAME_KEY, testName1);
        documentOne.put(DocumentMapper.PATH_KEY, testPath1);
        documentOne.put(DocumentMapper.TYPE_KEY, testType1);
        documentOne.put(DocumentMapper.ORIGIN_KEY, testOrigin1);

        String testOffset = "test-offset";
        String testPhrase = "test-phrase";
        String testMetaphorType = MetaphorType.DIRECT.name();
        String testExplanation = "test-explanation";

        Document metaphor = new Document();
        metaphor.put(DocumentMapper.OFFSET_KEY, testOffset);
        metaphor.put(DocumentMapper.PHRASE_KEY, testPhrase);
        metaphor.put(DocumentMapper.METAPHOR_TYPE_KEY, testMetaphorType);
        metaphor.put(DocumentMapper.EXPLANATION_KEY, testExplanation);
        documentOne.put(DocumentMapper.METAPHORS_KEY, List.of(metaphor));

        DOCUMENTS.add(documentOne);

        /// document 2 ///
        String testOid2 = "test-oid-2";
        String testDocStatus2 = DocumentStatus.PENDING.name();
        String testText2 = "test-text-2";
        String testName2 = "test-name-2";
        String testPath2 = "test-path-2";
        String testType2 = "test-type-2";
        String testOrigin2 = "test-origin-2";

        Document documentTwo = new Document();
        documentTwo.put(DocumentMapper.OBJECT_ID_KEY, testOid2);
        documentTwo.put(DocumentMapper.STATUS_KEY, testDocStatus2);
        documentTwo.put(DocumentMapper.TEXT_KEY, testText2);
        documentTwo.put(DocumentMapper.NAME_KEY, testName2);
        documentTwo.put(DocumentMapper.PATH_KEY, testPath2);
        documentTwo.put(DocumentMapper.TYPE_KEY, testType2);
        documentTwo.put(DocumentMapper.ORIGIN_KEY, testOrigin2);

        DOCUMENTS.add(documentTwo);

        /// document 3 ///
        String testOid3 = "test-oid-3";
        String testDocStatus3 = "invalid-status";
        String testText3 = "test-text-3";
        String testName3 = "test-name-3";
        String testPath3 = "test-path-3";
        String testType3 = "test-type-3";
        String testOrigin3 = "test-origin-3";

        Document documentThree = new Document();
        documentThree.put(DocumentMapper.OBJECT_ID_KEY, testOid3);
        documentThree.put(DocumentMapper.STATUS_KEY, testDocStatus3);
        documentThree.put(DocumentMapper.TEXT_KEY, testText3);
        documentThree.put(DocumentMapper.NAME_KEY, testName3);
        documentThree.put(DocumentMapper.PATH_KEY, testPath3);
        documentThree.put(DocumentMapper.TYPE_KEY, testType3);
        documentThree.put(DocumentMapper.ORIGIN_KEY, testOrigin3);

        DOCUMENTS.add(documentThree);

        /// document 4 ///
        String testOid4 = "test-oid-4";
        String testDocStatus4 = DocumentStatus.INCOMPLETE.name();
        String testText4 = "test-text-3";
        String testName4 = "test-name-3";
        String testPath4 = "test-path-3";
        String testType4 = "test-type-3";
        String testOrigin4 = "test-origin-3";

        Document documentFour = new Document();
        documentFour.put(DocumentMapper.OBJECT_ID_KEY, testOid4);
        documentFour.put(DocumentMapper.STATUS_KEY, testDocStatus4);
        documentFour.put(DocumentMapper.TEXT_KEY, testText4);
        documentFour.put(DocumentMapper.NAME_KEY, testName4);
        documentFour.put(DocumentMapper.PATH_KEY, testPath4);
        documentFour.put(DocumentMapper.TYPE_KEY, testType4);
        documentFour.put(DocumentMapper.ORIGIN_KEY, testOrigin4);

        String testOffsetFour = "test-offset-4";
        String testPhraseFOur = "test-phrase-4";
        String testMetaphorTypeFour = "invalid-metaphor-type";
        String testExplanationFour = "test-explanation-4";
        Document metaphorFour = new Document();
        metaphorFour.put(DocumentMapper.OFFSET_KEY, testOffsetFour);
        metaphorFour.put(DocumentMapper.PHRASE_KEY, testPhraseFOur);
        metaphorFour.put(DocumentMapper.METAPHOR_TYPE_KEY, testMetaphorTypeFour);
        metaphorFour.put(DocumentMapper.EXPLANATION_KEY, testExplanationFour);

        documentFour.put(DocumentMapper.METAPHORS_KEY, List.of(metaphorFour));

        DOCUMENTS.add(documentFour);

    }


    // TODO: complete
}