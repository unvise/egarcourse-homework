package com.unvise.dao;

import com.unvise.entity.PassengerEntity;
import org.hibernate.graph.GraphSemantic;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PassengerDao<ID> extends BaseDaoImpl<PassengerEntity, ID> {

    public PassengerDao() {
        super.setClazz(PassengerEntity.class);
    }

    public Optional<PassengerEntity> findPassengerWithFlightById(ID id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityGraph<?> graph = manager.getEntityGraph("passenger-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put(GraphSemantic.FETCH.getJpaHintName(), graph);
        return Optional.ofNullable(manager.find(PassengerEntity.class, id, properties));
    }

    public List<PassengerEntity> findPassengerByFirstNameAndLastName(String firstName, String lastName) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<PassengerEntity> query = cb.createQuery(PassengerEntity.class);
        Root<PassengerEntity> root = query.from(PassengerEntity.class);

        query.where(cb.and(
                cb.like(root.get("firstName"), firstName),
                cb.like(root.get("lastName"), lastName)
        ));

        return manager.createQuery(query).getResultList();
    }

    public List<PassengerEntity> findPassengersWithTicketsMoreThan(Integer ticketCount) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<PassengerEntity> query = cb.createQuery(PassengerEntity.class);
        Root<PassengerEntity> root = query.from(PassengerEntity.class);

        query.where(
                cb.greaterThan(cb.size(root.get("tickets")), ticketCount)
        );

        return manager.createQuery(query).getResultList();
    }

}
