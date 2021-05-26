package ng.misc.beneficiaries.config;

import ng.misc.beneficiaries.batch.BeneficiaryRecordProcessor;
import ng.misc.beneficiaries.batch.CompanyRecordProcessor;
import ng.misc.beneficiaries.batch.model.BeneficiaryRecord;
import ng.misc.beneficiaries.batch.model.CompanyRecord;
import ng.misc.beneficiaries.domain.model.Beneficiary;
import ng.misc.beneficiaries.domain.model.Company;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${beneficiaries.csv.input.file.name}")
    private String beneficiariesInputFileName;

    @Value("${register.csv.input.file.name}")
    private String registerInputFileName;

    @Bean
    public FlatFileItemReader<CompanyRecord> companyRecordReader() {
        return new FlatFileItemReaderBuilder<CompanyRecord>()
                .name("companyRecordReader")
                .resource(new FileSystemResource(registerInputFileName))
                .delimited()
                .delimiter(";")
                .names(
                        new String[]{"regCode",
                                "sepa",
                                "name",
                                "nameBeforeQuotes",
                                "nameInQuotes",
                                "nameAfterQuotes",
                                "withoutQuotes",
                                "regType",
                                "regTypeText",
                                "type",
                                "typeText",
                                "registered",
                                "terminated",
                                "closed",
                                "address",
                                "index",
                                "addressId",
                                "region",
                                "city",
                                "atvk",
                                "reregistrationTerm"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CompanyRecord>() {{
                    setTargetType(CompanyRecord.class);
                }})
                .linesToSkip(1)
                .build();
    }

    @Bean
    public FlatFileItemReader<BeneficiaryRecord> beneficiaryRecordReader() {
        return new FlatFileItemReaderBuilder<BeneficiaryRecord>()
                .name("beneficiaryRecordReader")
                .resource(new FileSystemResource(beneficiariesInputFileName))
                .delimited()
                .delimiter(";")
                .names(
                        new String[]{"id",
                                "legalEntityRegistrationNumber",
                                "forename",
                                "surname",
                                "latvianIdentityNumberMasked",
                                "birthDate",
                                "nationality",
                                "residence",
                                "registeredOn",
                                "lastModifiedAt"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<BeneficiaryRecord>() {{
                    setTargetType(BeneficiaryRecord.class);
                }})
                .linesToSkip(1)
                .build();
    }

    @Bean
    public CompanyRecordProcessor companyRecordProcessor() {
        return new CompanyRecordProcessor();
    }

    @Bean
    public BeneficiaryRecordProcessor beneficiaryRecordProcessor() {
        return new BeneficiaryRecordProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Company> companyWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Company>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO COMPANY " +
                        "(REG_CODE, " +
                        "SEPA, " +
                        "NAME, " +
                        "NAME_BEFORE_QUOTES, " +
                        "NAME_IN_QUOTES, " +
                        "NAME_AFTER_QUOTES, " +
                        "WITHOUT_QUOTES, " +
                        "REG_TYPE, " +
                        "REG_TYPE_TEXT, " +
                        "TYPE, " +
                        "TYPE_TEXT, " +
                        "REGISTERED, " +
                        "TERMINATED, " +
                        "CLOSED, " +
                        "ADDRESS, " +
                        "INDEX, " +
                        "ADDRESS_ID, " +
                        "REGION, " +
                        "CITY, " +
                        "ATVK) " +
                    "VALUES " +
                        "(:regCode, " +
                        ":sepa, " +
                        ":name, " +
                        ":nameBeforeQuotes, " +
                        ":nameInQuotes, " +
                        ":nameAfterQuotes, " +
                        ":withoutQuotes, " +
                        ":regType, " +
                        ":regTypeText, " +
                        ":type, " +
                        ":typeText, " +
                        ":registered, " +
                        ":terminated, " +
                        ":closed, " +
                        ":address, " +
                        ":index, " +
                        ":addressId, " +
                        ":region, " +
                        ":city, " +
                        ":atvk)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Beneficiary> beneficiaryWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Beneficiary>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO BENEFICIARY " +
                        "(LEGAL_ENTITY_REGISTRATION_NUMBER, " +
                        "FORENAME, " +
                        "SURNAME, " +
                        "LATVIAN_IDENTITY_NUMBER_MASKED, " +
                        "BIRTH_DATE, " +
                        "NATIONALITY, " +
                        "RESIDENCE, " +
                        "REGISTERED_ON, " +
                        "LAST_MODIFIED_AT) " +
                    "VALUES " +
                        "(:legalEntityRegistrationNumber, " +
                        ":forename, " +
                        ":surname, " +
                        ":latvianIdentityNumberMasked, " +
                        ":birthDate, " +
                        ":nationality, " +
                        ":residence, " +
                        ":registeredOn, " +
                        ":lastModifiedAt)")
                .dataSource(dataSource)
                .build();
    }

    @Bean("companiesStep")
    public Step companiesStep(JdbcBatchItemWriter<Company> writer) {
        return stepBuilderFactory.get("companiesStep")
                .<CompanyRecord, Company> chunk(10)
                .reader(companyRecordReader())
                .processor(companyRecordProcessor())
                .writer(writer)
                .build();
    }

    @Bean("beneficiariesStep")
    public Step beneficiariesStep(JdbcBatchItemWriter<Beneficiary> writer) {
        return stepBuilderFactory.get("beneficiariesStep")
                .<BeneficiaryRecord, Beneficiary> chunk(10)
                .reader(beneficiaryRecordReader())
                .processor(beneficiaryRecordProcessor())
                .writer(writer)
                .build();
    }
}
