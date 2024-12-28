package com.dustin.service;

import org.antlr.v4.runtime.misc.NotNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class JpaSpecification<T> {

    public Specification<T> conjunction() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public Specification<T> disjunction() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.disjunction();
    }

    public Specification<T> isNull(String field) {
        return (root, query, criteriaBuilder) -> root.get(field).isNull();
    }

    public Specification<T> isNotNull(String field) {
        return (root, query, criteriaBuilder) -> root.get(field).isNotNull();
    }

    /**
     * @return <field> IN (<selection>)
     */
    public Specification<T> in(String field, Set selection) {
        if (CollectionUtils.isEmpty(selection)) {
            return disjunction();
        }
        return inOrIgnore(field, selection);
    }

    /**
     * @return <field> IN (<selection>)
     * <selection> empty will ignore condition
     */
    public Specification<T> inOrIgnore(String field, Set selection) {
        return (root, query, criteriaBuilder) -> {
            if (CollectionUtils.isEmpty(selection)) {
                return criteriaBuilder.conjunction();
            }
            return root.get(field).in(selection);
        };
    }

    /**
     * @return <field> IN (<selection>)
     * <selection> empty will ignore condition
     */
    public Specification<T> notInOrIgnore(String field, Set selection) {
        return (root, query, criteriaBuilder) -> {
            if (CollectionUtils.isEmpty(selection)) {
                return criteriaBuilder.conjunction();
            }
            return root.get(field).in(selection).not();
        };
    }

    /**
     * @return [field] = [value]
     */
    public Specification<T> equals(String field, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(field), value);
    }

    /**
     * @return [field] = [value]
     */
    public Specification<T> equals(String clazz, String field, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(clazz).get(field), value);
    }

    /**
     * @return [field] = [value]
     */
    public Specification<T> notEqualsOrIgnore(String field, Object value) {
        if (value == null) {
            return conjunction();
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(field), value);
    }

    /**
     * @return [field] = [value]
     */
    public Specification<T> equalsOrIgnore(String field, Object value) {
        if (value == null) {
            return conjunction();
        }
        return equals(field, value);
    }

    /**
     * @return [field] = [value]
     */
    public Specification<T> equalsOrIgnore(String field, String value, boolean ignoreCase) {
        if (value == null) {
            return conjunction();
        }
        if (ignoreCase) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get(field)), value.toLowerCase());
        }
        return equalsOrIgnore(field, value);
    }

    /**
     * @return [field] LIKE '%[value]%'
     */
    public Specification<T> like(String field, String value) {
        if (StringUtils.isEmpty(value)) {
            return conjunction();
        }
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    /**
     * @return [field] <= [value]
     */
    public <Y extends Comparable<? super Y>> Specification<T> lessThanOrEqualTo(String field, @NotNull Y value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(field), value);
    }

    /**
     * @return [field] >= [value]
     */
    public <Y extends Comparable<? super Y>> Specification<T> greaterThanOrEqualTo(String field, @NotNull Y value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(field), value);
    }

    /**
     * @return [field] >= [valueFrom] AND [field] <= [valueTo]
     */
    public <Y extends Comparable<? super Y>> Specification<T> betweenOrEquals(String field, Y valueFrom, Y valueTo) {
        Specification<T> periodEndFromSpec = conjunction();
        if (valueFrom != null) {
            periodEndFromSpec = greaterThanOrEqualTo(field, valueFrom);
        }
        Specification<T> periodEndToSpec = conjunction();
        if (valueTo != null) {
            periodEndToSpec = lessThanOrEqualTo(field, valueTo);
        }
        return periodEndFromSpec.and(periodEndToSpec);
    }


}
