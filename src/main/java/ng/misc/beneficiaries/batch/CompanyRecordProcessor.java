package ng.misc.beneficiaries.batch;

import ng.misc.beneficiaries.batch.model.CompanyRecord;
import ng.misc.beneficiaries.domain.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Date;

public class CompanyRecordProcessor implements ItemProcessor<CompanyRecord, Company> {
    private static final Logger logger = LoggerFactory.getLogger(CompanyRecordProcessor.class);
    private long recordCount = 0;
    @Override
    public Company process(final CompanyRecord companyRecord) throws Exception {
        Company company = new Company();
        company.setRegCode(companyRecord.getRegCode());
        company.setSepa(companyRecord.getSepa());
        company.setName(companyRecord.getName());
        company.setNameBeforeQuotes(companyRecord.getNameBeforeQuotes());
        company.setNameInQuotes(companyRecord.getNameInQuotes());
        company.setNameAfterQuotes(companyRecord.getNameAfterQuotes());
        company.setWithoutQuotes(companyRecord.getWithoutQuotes());
        company.setRegType(companyRecord.getRegType());
        company.setRegTypeText(companyRecord.getRegTypeText());
        company.setType(companyRecord.getType());
        company.setTypeText(companyRecord.getTypeText());
        company.setRegistered(toDate(companyRecord.getTypeText()));
        company.setTerminated(toDate(companyRecord.getTypeText()));
        company.setClosed(companyRecord.getClosed());
        company.setAddress(companyRecord.getAddress());
        company.setIndex(toLong(companyRecord.getIndex()));
        company.setAddressId(toLong(companyRecord.getAddressId()));
        company.setRegion(toLong(companyRecord.getRegion()));
        company.setCity(toLong(companyRecord.getCity()));
        company.setAtvk(companyRecord.getAtvk());
        company.setReRegistrationTerm(toDate(companyRecord.getReregistrationTerm()));

        ++recordCount;
        if (recordCount % 10000 == 0) {
            logger.info("Transformed {} record(s)", ++recordCount);
        }

        return company;
    }

    private Date toDate(String value) {
        try {
            return Date.valueOf(value);
        } catch (IllegalArgumentException e) {}
        return null;
    }

    private Long toLong(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {}
        return null;
    }
}
