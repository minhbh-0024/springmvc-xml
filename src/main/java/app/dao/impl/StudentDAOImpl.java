package app.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import app.dao.GenericDAO;
import app.dao.StudentDAO;
import app.model.Student;

public class StudentDAOImpl extends GenericDAO<Integer, Student> implements StudentDAO {
	private static final Logger logger = Logger.getLogger(StudentDAOImpl.class);

	public StudentDAOImpl() {
		super(Student.class);
	}

	public StudentDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}

	@Override
	public Student findByEmail(String email) {
		logger.info("email: " + email);
		return (Student) getSession().createQuery("FROM Student where email = :email").setParameter("email", email)
				.getSingleResult();
	}

	@Override
	public List<Student> searchStudentUsingCretial(String name, int gender) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Student> cr = builder.createQuery(Student.class);
		Root<Student> root = cr.from(Student.class);
		cr.select(root);

		Predicate nameRestriction = builder.and(builder.like(root.get("name"), "%" + name + "%"));
		Predicate genderRestriction = builder.and(builder.equal(root.get("gender"), gender));

		cr.where(nameRestriction, genderRestriction);
		return getSession().createQuery(cr).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> loadStudents() {
		return getSession().createQuery("from Student").getResultList();
	}

	// minh
	@Override
	public List<Student> findByInfo(String info) {
		logger.info("info: " + info);

		/*
		 * Criteria cr = getSession().createCriteria(Student.class);
		 * 
		 * Criterion crEmail = Restrictions.like("email", "%" + info + "%"); Criterion
		 * crName = Restrictions.like("name", "%" + info + "%");
		 * 
		 * cr.add(Restrictions.or(crEmail, crName));
		 * 
		 * return cr.list();
		 */
		
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		query.select(root);
		
		Predicate restriction = builder.or(builder.like(root.get("name"), "%" + info + "%"), builder.like(root.get("email"), "%" + info + "%"));

		query.where(restriction);
		
		return getSession().createQuery(query).getResultList();
		
	}

}
