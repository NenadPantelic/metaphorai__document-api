package ai.metaphor.document_api.filter;

import ai.metaphor.document_api.exception.FilterException;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;

class FilterQueryParserTest {

    private final FilterQueryParser parser = new FilterQueryParser();

    @Test
    public void givenEmptyFilterWithoutSortParamsWhenParsedShouldReturnOnlyProjectedFields() {
        String[] fields = {"a", "b", "c"};
        int page = 0;
        int limit = 20;
        Query query = parser.parseFilter(
                null, null, null, page, limit, fields
        );

        Query expectedQuery = new Query();
        expectedQuery.fields().include(fields);
        expectedQuery.with(PageRequest.of(page, limit));

        Assertions.assertThat(query).isEqualTo(expectedQuery);
    }

    @Test
    public void givenFilterWithAllValidOperatorsAndSortWhenParsedShouldReturnProperlyParsedQuery() {
        String[] fields = {"a", "b", "c"};
        int page = 2;
        int limit = 30;

        String filter = "k1==v1;k2!=v2;k3~v3;k4=gt=v4;k5=gte=v5;k6=lt=v6;k7=lte=v7";
        String sortBy = "a";
        String sortDirection = "DESC";

        Query query = parser.parseFilter(
                filter, sortBy, sortDirection, page, limit, fields
        );

        Document queryObject = query.getQueryObject();

        // k1==v1
        Assertions.assertThat(queryObject.containsKey("k1")).isTrue();
        Assertions.assertThat(queryObject.getString("k1")).isEqualTo("v1");

        // k2!=v2
        Assertions.assertThat(queryObject.containsKey("k2")).isTrue();
        Document condition2 = (Document) queryObject.get("k2");
        Assertions.assertThat(condition2.getString("$ne")).isEqualTo("v2");

        // k3~v3
        // NOTE: internal type with private access
        Assertions.assertThat(queryObject.containsKey("k3")).isTrue();

        // k4=gt=v4
        Assertions.assertThat(queryObject.containsKey("k4")).isTrue();
        Document condition4 = (Document) queryObject.get("k4");
        Assertions.assertThat(condition4.getString("$gt")).isEqualTo("v4");

        // k5=gte=v5
        Assertions.assertThat(queryObject.containsKey("k5")).isTrue();
        Document condition5 = (Document) queryObject.get("k5");
        Assertions.assertThat(condition5.getString("$gte")).isEqualTo("v5");

        // k6=lt=v6
        Assertions.assertThat(queryObject.containsKey("k6")).isTrue();
        Document condition6 = (Document) queryObject.get("k6");
        Assertions.assertThat(condition6.getString("$lt")).isEqualTo("v6");

        // k7=lte=v7
        Assertions.assertThat(queryObject.containsKey("k7")).isTrue();
        Document condition7 = (Document) queryObject.get("k7");
        Assertions.assertThat(condition7.getString("$lte")).isEqualTo("v7");

        // 'a': 1, 'b': 1, 'c': 1
        Document fieldsObject = query.getFieldsObject();
        Assertions.assertThat(fieldsObject.getInteger("a")).isEqualTo(1);
        Assertions.assertThat(fieldsObject.getInteger("b")).isEqualTo(1);
        Assertions.assertThat(fieldsObject.getInteger("c")).isEqualTo(1);

        // {'a': -1} -> descending sort
        Document sortObject = query.getSortObject();
        Assertions.assertThat(sortObject.getInteger("a")).isEqualTo(-1);

        int queryLimit = query.getLimit();
        Assertions.assertThat(queryLimit).isEqualTo(limit);

        // skip -> page * limit
        long skip = query.getSkip();
        Assertions.assertThat(skip).isEqualTo(page * limit);
    }

    @Test
    public void givenInvalidSortDirectionWhenParsedShouldThrowFilterException() {
        Assertions.assertThatThrownBy(() -> parser.parseFilter(
                null, "a", "UNKNOWN DIRECTION", 0, 1, "a", "b", "c"
        )).isInstanceOf(FilterException.class);
    }
}