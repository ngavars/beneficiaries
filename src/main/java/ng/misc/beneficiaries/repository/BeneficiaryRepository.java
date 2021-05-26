package ng.misc.beneficiaries.repository;

import ng.misc.beneficiaries.domain.model.Beneficiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Transactional
@Repository
public class BeneficiaryRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Beneficiary> findByRegistrationNumber(String registrationNumber) {
        return entityManager.createQuery("from Beneficiary where legalEntityRegistrationNumber=:registrationNumber")
                .setParameter("registrationNumber", registrationNumber)
                .getResultList();
    }

    public List<Beneficiary> findByNameAndSurnameAndDirthDate(String searchString, Date birthDate) {
        Query query = entityManager.createQuery("from Beneficiary where" +
                " forename like :searchString or " +
                " surname like :searchString " + (birthDate == null ? "" : "and birthDate=:birthDate") )
                .setParameter("searchString", searchString);
        if (birthDate != null) {
            query.setParameter("birthDate", birthDate);
        }
        return query.getResultList();
    }
}
