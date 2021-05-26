package ng.misc.beneficiaries.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "BENEFICIARY")
public class Beneficiary implements Serializable {
    private Long id;
    private String legalEntityRegistrationNumber;
    private String forename;
    private String surname;
    private String latvianIdentityNumberMasked;
    private Date birthDate;
    private String nationality;
    private String residence;
    private Date registeredOn;
    private Date lastModifiedAt;

    public Beneficiary() {
    }

    public Beneficiary(String legalEntityRegistrationNumber, String forename, String surname, String latvianIdentityNumberMasked, Date birthDate, String nationality, String residence, Date registeredOn, Date lastModifiedAt) {
        this.legalEntityRegistrationNumber = legalEntityRegistrationNumber;
        this.forename = forename;
        this.surname = surname;
        this.latvianIdentityNumberMasked = latvianIdentityNumberMasked;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.residence = residence;
        this.registeredOn = registeredOn;
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getLegalEntityRegistrationNumber() {
        return legalEntityRegistrationNumber;
    }

    public void setLegalEntityRegistrationNumber(String legalEntityRegistrationNumber) {
        this.legalEntityRegistrationNumber = legalEntityRegistrationNumber;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLatvianIdentityNumberMasked() {
        return latvianIdentityNumberMasked;
    }

    public void setLatvianIdentityNumberMasked(String latvianIdentityNumberMasked) {
        this.latvianIdentityNumberMasked = latvianIdentityNumberMasked;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
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
