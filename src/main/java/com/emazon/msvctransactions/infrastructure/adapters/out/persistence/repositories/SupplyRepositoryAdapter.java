package com.emazon.msvctransactions.infrastructure.adapters.out.persistence.repositories;

import com.emazon.msvctransactions.domain.enums.SupplyStatus;
import com.emazon.msvctransactions.domain.models.Supply;
import com.emazon.msvctransactions.domain.ports.out.repositories.SupplyRepository;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.entities.SupplyEntity;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.jpa.JpaSupplyRepository;
import com.emazon.msvctransactions.infrastructure.adapters.out.persistence.mappers.SupplyEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.emazon.msvctransactions.infrastructure.adapters.out.persistence.utils.constants.SupplyRepositoryConstant.*;

@Repository
@AllArgsConstructor
public class SupplyRepositoryAdapter implements SupplyRepository {
  private final JpaSupplyRepository repository;
  private final SupplyEntityMapper mapper;
  private final EntityManager entityManager;
  @Override
  public Supply saveSupply(Supply supply) {
    SupplyEntity supplyEntityToSave = mapper.toEntity(supply);
    return mapper.toDomain(
            repository.save(supplyEntityToSave)
    );
  }

  @Override
  public List<Supply> getSuppliesByStatus(SupplyStatus status) {
    return mapper.toDomain(
            repository.findByStatus(status)
    );
  }

  @Override
  public Optional<Supply> getNextAvailableSupplyForArticle(Long articleId) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<SupplyEntity> criteriaQuery = criteriaBuilder.createQuery(SupplyEntity.class);
    Root<SupplyEntity> root = criteriaQuery.from(SupplyEntity.class);

    criteriaQuery.select(root)
            .where(
                    criteriaBuilder.equal(root.get(SUPPLY_ARTICLE_ID), articleId),
                    criteriaBuilder.equal(root.get(SUPPLY_STATUS), SupplyStatus.PENDING),
                    criteriaBuilder.greaterThanOrEqualTo(root.get(SUPPLY_AVAILABLE_AT), LocalDateTime.now())
            )
            .orderBy(criteriaBuilder.asc(root.get(SUPPLY_AVAILABLE_AT)));

    try {
      SupplyEntity supplyEntity = entityManager.createQuery(criteriaQuery)
              .setMaxResults(1)
              .getSingleResult();
      return Optional.of(mapper.toDomain(supplyEntity));
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
