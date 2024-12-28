package com.dustin.service;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification {
    private GenericSpecification() {
    }

    public static <T> Specification<T> conjunction() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static <T, ID> Specification<T> isActive() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(buildIsActivePredicate(root, criteriaBuilder));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    private static <T> Predicate buildIsActivePredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("isDeleted"), false);
    }

    public static <T> Specification<T> isCreator(String creator) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdBy"), creator);
    }


}
