package org.harvanir.pattern.clean.jpa.support;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Composite of {@link JpaRepositoryImplementation} and {@link QuerydslPredicateExecutor}. This
 * class is designed to override default {@link Transactional#propagation()} from {@link
 * Propagation#REQUIRED} to {@link Propagation#SUPPORTS}.
 *
 * @author Harvan Irsyadi
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JpaEnhancedRepository<T, K extends Serializable>
    implements QueryDslPredicateAndProjectionExecutor<T>, JpaRepositoryImplementation<T, K> {

  private final EntityPath<T> path;

  private final Querydsl querydsl;

  private final JpaRepositoryImplementation<T, K> jpaRepositoryDelegate;

  private final QuerydslPredicateExecutor<T> queryDslPredicateExecutorDelegate;

  public JpaEnhancedRepository(
      JpaEntityInformation<T, K> entityInformation, EntityManager entityManager) {
    EntityPathResolver entityPathResolver = SimpleEntityPathResolver.INSTANCE;

    path = entityPathResolver.createPath(entityInformation.getJavaType());
    querydsl = new Querydsl(entityManager, new PathBuilder<>(path.getType(), path.getMetadata()));
    jpaRepositoryDelegate = new SimpleJpaRepository<>(entityInformation, entityManager);
    queryDslPredicateExecutorDelegate =
        new QuerydslJpaPredicateExecutor<>(
            entityInformation, entityManager, entityPathResolver, null);
  }

  @Override
  public <P> List<P> findAll(FactoryExpression<P> factoryExpression) {
    return querydsl.createQuery(path).select(factoryExpression).fetch();
  }

  @Override
  public <P> List<P> findAll(FactoryExpression<P> factoryExpression, Predicate predicate) {
    return querydsl.createQuery(path).select(factoryExpression).where(predicate).fetch();
  }

  @Override
  public <P> Page<P> findAll(FactoryExpression<P> factoryExpression, Pageable pageable) {
    JPQLQuery<P> query = querydsl.createQuery(path).select(factoryExpression);
    query = querydsl.applyPagination(pageable, query);

    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  @Override
  public <P> Page<P> findAll(
      FactoryExpression<P> factoryExpression, Predicate predicate, Pageable pageable) {
    JPQLQuery<P> query = querydsl.createQuery(path).select(factoryExpression).where(predicate);

    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  @Override
  public <P> P findOne(FactoryExpression<P> factoryExpression, Predicate predicate) {
    return querydsl.createQuery(path).select(factoryExpression).where(predicate).fetchOne();
  }

  @Nonnull
  @Override
  public Optional<T> findOne(@Nonnull Predicate predicate) {
    return queryDslPredicateExecutorDelegate.findOne(predicate);
  }

  @Nonnull
  @Override
  public Iterable<T> findAll(@Nonnull Predicate predicate) {
    return queryDslPredicateExecutorDelegate.findAll(predicate);
  }

  @Nonnull
  @Override
  public Iterable<T> findAll(@Nonnull Predicate predicate, @Nonnull Sort sort) {
    return queryDslPredicateExecutorDelegate.findAll(predicate, sort);
  }

  @Nonnull
  @Override
  public Iterable<T> findAll(@Nonnull Predicate predicate, @Nonnull OrderSpecifier<?>... orders) {
    return queryDslPredicateExecutorDelegate.findAll(predicate, orders);
  }

  @Nonnull
  @Override
  public Iterable<T> findAll(@Nonnull OrderSpecifier<?>... orders) {
    return queryDslPredicateExecutorDelegate.findAll(orders);
  }

  @Nonnull
  @Override
  public Page<T> findAll(@Nonnull Predicate predicate, @Nonnull Pageable pageable) {
    return queryDslPredicateExecutorDelegate.findAll(predicate, pageable);
  }

  @Override
  public long count(@Nonnull Predicate predicate) {
    return queryDslPredicateExecutorDelegate.count(predicate);
  }

  @Override
  public boolean exists(@Nonnull Predicate predicate) {
    return queryDslPredicateExecutorDelegate.exists(predicate);
  }

  @Override
  public void setRepositoryMethodMetadata(@Nonnull CrudMethodMetadata crudMethodMetadata) {
    jpaRepositoryDelegate.setRepositoryMethodMetadata(crudMethodMetadata);
  }

  @Nonnull
  @Override
  public List<T> findAll() {
    return jpaRepositoryDelegate.findAll();
  }

  @Nonnull
  @Override
  public List<T> findAll(@Nonnull Sort sort) {
    return jpaRepositoryDelegate.findAll(sort);
  }

  @Nonnull
  @Override
  public Page<T> findAll(@Nonnull Pageable pageable) {
    return jpaRepositoryDelegate.findAll(pageable);
  }

  @Nonnull
  @Override
  public List<T> findAllById(@Nonnull Iterable<K> ks) {
    return jpaRepositoryDelegate.findAllById(ks);
  }

  @Override
  public long count() {
    return jpaRepositoryDelegate.count();
  }

  @Transactional
  @Override
  public void deleteById(@Nonnull K k) {
    jpaRepositoryDelegate.deleteById(k);
  }

  @Transactional
  @Override
  public void delete(@Nonnull T entity) {
    jpaRepositoryDelegate.delete(entity);
  }

  @Transactional
  @Override
  public void deleteAll(@Nonnull Iterable<? extends T> entities) {
    jpaRepositoryDelegate.deleteAll(entities);
  }

  @Transactional
  @Override
  public void deleteAll() {
    jpaRepositoryDelegate.deleteAll();
  }

  @Nonnull
  @Transactional
  @Override
  public <S extends T> S save(@Nonnull S entity) {
    return jpaRepositoryDelegate.save(entity);
  }

  @Nonnull
  @Transactional
  @Override
  public <S extends T> List<S> saveAll(@Nonnull Iterable<S> entities) {
    return jpaRepositoryDelegate.saveAll(entities);
  }

  @Nonnull
  @Override
  public Optional<T> findById(@Nonnull K k) {
    return jpaRepositoryDelegate.findById(k);
  }

  @Override
  public boolean existsById(@Nonnull K k) {
    return jpaRepositoryDelegate.existsById(k);
  }

  @Transactional
  @Override
  public void flush() {
    jpaRepositoryDelegate.flush();
  }

  @Nonnull
  @Transactional
  @Override
  public <S extends T> S saveAndFlush(@Nonnull S entity) {
    return jpaRepositoryDelegate.saveAndFlush(entity);
  }

  @Transactional
  @Override
  public void deleteInBatch(@Nonnull Iterable<T> entities) {
    jpaRepositoryDelegate.deleteInBatch(entities);
  }

  @Transactional
  @Override
  public void deleteAllInBatch() {
    jpaRepositoryDelegate.deleteAllInBatch();
  }

  @Nonnull
  @Override
  public T getOne(@Nonnull K k) {
    return jpaRepositoryDelegate.getOne(k);
  }

  @Nonnull
  @Override
  public <S extends T> Optional<S> findOne(@Nonnull Example<S> example) {
    return jpaRepositoryDelegate.findOne(example);
  }

  @Nonnull
  @Override
  public <S extends T> List<S> findAll(@Nonnull Example<S> example) {
    return jpaRepositoryDelegate.findAll(example);
  }

  @Nonnull
  @Override
  public <S extends T> List<S> findAll(@Nonnull Example<S> example, @Nonnull Sort sort) {
    return jpaRepositoryDelegate.findAll(example, sort);
  }

  @Nonnull
  @Override
  public <S extends T> Page<S> findAll(@Nonnull Example<S> example, @Nonnull Pageable pageable) {
    return jpaRepositoryDelegate.findAll(example, pageable);
  }

  @Override
  public <S extends T> long count(@Nonnull Example<S> example) {
    return jpaRepositoryDelegate.count(example);
  }

  @Override
  public <S extends T> boolean exists(@Nonnull Example<S> example) {
    return jpaRepositoryDelegate.exists(example);
  }

  @Nonnull
  @Override
  public Optional<T> findOne(Specification<T> spec) {
    return jpaRepositoryDelegate.findOne(spec);
  }

  @Nonnull
  @Override
  public List<T> findAll(Specification<T> spec) {
    return jpaRepositoryDelegate.findAll(spec);
  }

  @Nonnull
  @Override
  public Page<T> findAll(Specification<T> spec, @Nonnull Pageable pageable) {
    return jpaRepositoryDelegate.findAll(spec, pageable);
  }

  @Nonnull
  @Override
  public List<T> findAll(Specification<T> spec, @Nonnull Sort sort) {
    return jpaRepositoryDelegate.findAll(spec, sort);
  }

  @Override
  public long count(Specification<T> spec) {
    return jpaRepositoryDelegate.count(spec);
  }
}
