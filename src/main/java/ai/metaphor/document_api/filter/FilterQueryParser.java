package ai.metaphor.document_api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
@Slf4j
public class FilterQueryParser {

    private static final String FILTER_SEPARATOR = ";";

    private static final String COLLATION_LANGUAGE = "en";

    private static final BiFunction<String, String, Criteria> EQ = (key, value) -> where(key).is(value);
    private static final BiFunction<String, String, Criteria> NEQ = (key, value) -> where(key).ne(value);
    private static final BiFunction<String, String, Criteria> GT = (key, value) -> where(key).gt(value);
    private static final BiFunction<String, String, Criteria> GTE = (key, value) -> where(key).gte(value);
    private static final BiFunction<String, String, Criteria> LT = (key, value) -> where(key).lt(value);
    private static final BiFunction<String, String, Criteria> LTE = (key, value) -> where(key).lte(value);
    private static final BiFunction<String, String, Criteria> ALIKE = (key, value) -> where(key).alike(Example.of(value));

    private static final Map<String, BiFunction<String, String, Criteria>> OPERATOR_MAP = Map.of(
            "==", EQ,
            "!=", NEQ,
            "~", ALIKE,
            "=gt=", GT,
            "=gte=", GTE,
            "=lt=", LT,
            "=lte=", LTE
    );

    public Query parseFilter(String filter, String sortBy, String sortDirection, int page, int limit, String... fields) {
        Query query = new Query();
        query.fields().include(fields);

        if (isBlank(filter)) {
            return query;
        }

        List<Triple> criteriaTriples = extractFilterCriteria(filter);
        Criteria criteria = new Criteria();

        for (Triple triple : criteriaTriples) {
            String key = triple.first;
            String value = triple.third;
            BiFunction<String, String, Criteria> biFunction = OPERATOR_MAP.get(triple.second);
            if (biFunction == null) {
                continue;
            }

            criteria.andOperator(biFunction.apply(key, value));
        }
        query.addCriteria(criteria);

        if (!isBlank(sortBy) && !isBlank(sortDirection)) {
            Sort sort = Sort.by(
                    new Sort.Order(Sort.Direction.valueOf(sortDirection), sortBy).ignoreCase()
            );
            // TODO: check this (does it support CI sorting)
            query.collation(
                    Collation.of(COLLATION_LANGUAGE).strength(Collation.ComparisonLevel.secondary())
            );
            Pageable pageable = PageRequest.of(page, limit, sort);
            query.with(pageable);
        }

        return query;
    }

    // lhs_1<op_1>rhs_1;lhs_2<op_2>rhs_2....
    // -> [lhs_1, op_1, rhs_1], [lhs_2, op_2, rhs_2]
    private List<Triple> extractFilterCriteria(String filter) {
        // TODO
        return List.of();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    // make it generic
    private record Triple(String first,
                          String second,
                          String third) {
    }

}
