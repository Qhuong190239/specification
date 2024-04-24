package com.example.specification_v3.services;

import com.example.specification_v3.entities.Coffee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CoffeeService {

    @PersistenceContext
    EntityManager entityManager;

    public Coffee getCoffeeById(long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Coffee> query = builder.createQuery(Coffee.class);
        Root<Coffee> root = query.from(Coffee.class);

        Predicate predicate = builder.equal(root.get("ID"),id);

        query.select(root).where(predicate);
        return entityManager.createQuery(query).getSingleResult();
    }

    public Collection<Coffee> getCoffeeByComplexCondition(String name, Coffee.CoffeeType type) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Coffee> query = builder.createQuery(Coffee.class);
        Root<Coffee> root = query.from(Coffee.class);

        Predicate hasNamelike = builder.like(root.get("name"), name);
        Predicate hasType = builder.equal(root.get("type"), type);

        Predicate predicate = builder.and(hasNamelike, hasType);

        query.select(root).where(predicate);
        return entityManager.createQuery(query).getResultList();
    }
}
