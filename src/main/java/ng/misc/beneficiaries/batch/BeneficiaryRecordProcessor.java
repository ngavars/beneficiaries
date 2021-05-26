package ng.misc.beneficiaries.batch;

import ng.misc.beneficiaries.batch.model.BeneficiaryRecord;
import ng.misc.beneficiaries.domain.model.Beneficiary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeneficiaryRecordProcessor implements ItemProcessor<BeneficiaryRecord, Beneficiary> {
    private static final Logger logger = LoggerFactory.getLogger(BeneficiaryRecordProcessor.class);
    private long recordCount = 0;

    private static SimpleDateFormat dateParser= new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dateTimeParser= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Beneficiary process(final BeneficiaryRecord beneficiaryRecord) throws Exception {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setLegalEntityRegistrationNumber(beneficiaryRecord.getLegalEntityRegistrationNumber());
        beneficiary.setForename(beneficiaryRecord.getForename());
        beneficiary.setSurname(beneficiaryRecord.getSurname());
        beneficiary.setLatvianIdentityNumberMasked(beneficiaryRecord.getLatvianIdentityNumberMasked());
        beneficiary.setBirthDate(parseDate(beneficiaryRecord.getBirthDate()));
        beneficiary.setNationality(beneficiaryRecord.getNationality());
        beneficiary.setResidence(beneficiaryRecord.getResidence());
        beneficiary.setRegisteredOn(parseDate(beneficiaryRecord.getRegisteredOn()));
        beneficiary.setLastModifiedAt(parseDateTime(beneficiaryRecord.getLastModifiedAt()));

        ++recordCount;
        if (recordCount % 10000 == 0) {
            logger.info("Transformed {} record(s)", ++recordCount);
        }

        return beneficiary;
    }

    private Date parseDate(String value) {
        try {
            return dateParser.parse(value);
        } catch (ParseException e) {}
        return null;
    }
    private Date parseDateTime(String value) {
        try {
            return dateTimeParser.parse(value);
        } catch (ParseException e) {}
        return null;
    }

}
