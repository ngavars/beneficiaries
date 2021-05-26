package ng.misc.beneficiaries.resource;

import ng.misc.beneficiaries.domain.model.Beneficiary;
import ng.misc.beneficiaries.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value ="beneficiaries", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeneficiaryResource {

    @Autowired
    private BeneficiaryRepository repository;

    @RequestMapping(value = "/{registrationNumber}", method = RequestMethod.GET)
    public List<Beneficiary> findByRegistrationNumber(@PathVariable String registrationNumber) {
        return repository.findByRegistrationNumber(registrationNumber);
    }
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Beneficiary> findByNameAndSurnameAndBirthDate(
            @RequestParam String searchString, @RequestParam(required = false) Date birthDate) {
        return repository.findByNameAndSurnameAndDirthDate(searchString, birthDate);
    }
}
