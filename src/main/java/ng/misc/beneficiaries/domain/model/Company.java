package ng.misc.beneficiaries.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {
    private String regCode;
    private String sepa;
    private String name;
    private String nameBeforeQuotes;
    private String nameInQuotes;
    private String nameAfterQuotes;
    private String withoutQuotes;
    private String regType;
    private String regTypeText;
    private String type;
    private String typeText;
    private Date registered;
    private Date terminated;
    private String closed;
    private String address;
    private Long index;
    private Long addressId;
    private Long region;
    private Long city;
    private String atvk;
    private Date reRegistrationTerm;
    private Long id;
    private List<Beneficiary> beneficiaryList;

    public Company() {
    }

    public Company(String regCode, String sepa, String name, String nameBeforeQuotes, String nameInQuotes, String nameAfterQuotes, String withoutQuotes, String regType, String regTypeText, String type, String typeText, Date registered, Date terminated, String closed, String address, Long index, Long addressId, Long region, Long city, String atvk, Date reRegistrationTerm) {
        this.regCode = regCode;
        this.sepa = sepa;
        this.name = name;
        this.nameBeforeQuotes = nameBeforeQuotes;
        this.nameInQuotes = nameInQuotes;
        this.nameAfterQuotes = nameAfterQuotes;
        this.withoutQuotes = withoutQuotes;
        this.regType = regType;
        this.regTypeText = regTypeText;
        this.type = type;
        this.typeText = typeText;
        this.registered = registered;
        this.terminated = terminated;
        this.closed = closed;
        this.address = address;
        this.index = index;
        this.addressId = addressId;
        this.region = region;
        this.city = city;
        this.atvk = atvk;
        this.reRegistrationTerm = reRegistrationTerm;
    }

    @NaturalId
    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getSepa() {
        return sepa;
    }

    public void setSepa(String sepa) {
        this.sepa = sepa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameBeforeQuotes() {
        return nameBeforeQuotes;
    }

    public void setNameBeforeQuotes(String nameBeforeQuotes) {
        this.nameBeforeQuotes = nameBeforeQuotes;
    }

    public String getNameInQuotes() {
        return nameInQuotes;
    }

    public void setNameInQuotes(String nameInQuotes) {
        this.nameInQuotes = nameInQuotes;
    }

    public String getNameAfterQuotes() {
        return nameAfterQuotes;
    }

    public void setNameAfterQuotes(String nameAfterQuotes) {
        this.nameAfterQuotes = nameAfterQuotes;
    }

    public String getWithoutQuotes() {
        return withoutQuotes;
    }

    public void setWithoutQuotes(String withoutQuotes) {
        this.withoutQuotes = withoutQuotes;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getRegTypeText() {
        return regTypeText;
    }

    public void setRegTypeText(String regTypeText) {
        this.regTypeText = regTypeText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Date getTerminated() {
        return terminated;
    }

    public void setTerminated(Date terminated) {
        this.terminated = terminated;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getAtvk() {
        return atvk;
    }

    public void setAtvk(String atvk) {
        this.atvk = atvk;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public Date getReRegistrationTerm() {
        return reRegistrationTerm;
    }

    public void setReRegistrationTerm(Date reRegistrationTerm) {
        this.reRegistrationTerm = reRegistrationTerm;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @JsonIgnore
    public Long getId() {
        return id;
    }
}
