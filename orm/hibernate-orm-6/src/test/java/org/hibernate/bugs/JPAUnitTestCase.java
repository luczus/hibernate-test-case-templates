package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhhXXX() {
		long parent1Id = saveParent1();

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Parent1 parent1 = Objects.requireNonNull(entityManager.find(Parent1.class, parent1Id));

		Child sharedChild = new Child();
		sharedChild.setSomeData("someData");

		parent1.setChild(sharedChild);

		Parent2 parent2 = new Parent2();
		parent2.setChild(sharedChild);

		entityManager.merge(parent1);
		entityManager.persist(parent2);

		entityManager.getTransaction().commit();
	}

	private Long saveParent1() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Parent1 parent1 = new Parent1();
		entityManager.persist(parent1);

		entityManager.getTransaction().commit();
		entityManager.close();
		return parent1.getId();
	}


}
