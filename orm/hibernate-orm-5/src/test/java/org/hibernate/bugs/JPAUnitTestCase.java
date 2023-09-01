package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public void hhh16595Test_v5() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		initData(entityManager);

		entityManager.getTransaction().begin();
		long startTime = System.currentTimeMillis();
		List resultList = entityManager.createQuery("select f from Fact f " +
				" LEFT JOIN FETCH f.dimensionMembers" +
						" WHERE f.branch=333")
				.getResultList();
		long endTime = System.currentTimeMillis();
		entityManager.getTransaction().commit();

		long elapsed = endTime - startTime;
		Assert.assertTrue("executing query took: " + elapsed + ", size: " + resultList, elapsed < 60_000);
	}

	private void initData(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		List<DimensionMember> members = new ArrayList<>();
		SecureRandom secureRandom = new SecureRandom();
		for (int i = 0; i < 1000; i++) {
			DimensionMember dimensionMember = new DimensionMember();
			dimensionMember.setId((long) i);
			entityManager.persist(dimensionMember);
			members.add(dimensionMember);
		}
		for (int i =0; i < 30_000;i++){
			Fact fact= new Fact();
			fact.setId((long) i);
			fact.setBranch(333);

			fact.setDimensionMembers( secureRandom.ints(10, 0, members.size())
					.mapToObj(members::get)
					.collect(Collectors.toSet()));
			entityManager.persist(fact);
		}
		entityManager.getTransaction().commit();
	}
}
