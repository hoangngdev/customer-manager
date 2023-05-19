package hoa.demo.cm.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hoa.demo.cm.data.entity.ABContact;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class ContactService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<ABContact> findContactByNameAndEmail(String filteredName, String filteredEMail) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ABContact> criteriaQuery = criteriaBuilder.createQuery(ABContact.class);
		Root<ABContact> itemRoot = criteriaQuery.from(ABContact.class);

		if (filteredEMail != null && !filteredEMail.isEmpty()) {
			Predicate predicateForEmail = criteriaBuilder.like(itemRoot.get("email"), filteredEMail);
			criteriaQuery.where(predicateForEmail);
		}

		List<ABContact> temp = entityManager.createQuery(criteriaQuery).getResultList();
		if (filteredName != null && !filteredName.isEmpty()) {
			temp.removeIf(e -> !e.getDisplayName().contains(filteredName));

		}
		System.out.println("temp result " + temp.size());
		return temp;
	}
}
