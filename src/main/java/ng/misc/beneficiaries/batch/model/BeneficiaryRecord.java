package ng.misc.beneficiaries.batch.model;

public class BeneficiaryRecord {
    private String id;
    private String legalEntityRegistrationNumber;
    private String forename;
    private String surname;
    private String latvianIdentityNumberMasked;
    private String birthDate;
    private String nationality;
    private String residence;
    private String registeredOn;
    private String lastModifiedAt;

    public BeneficiaryRecord() {
    }

    public BeneficiaryRecord(String legalEntityRegistrationNumber, String forename, String surname, String latvianIdentityNumberMasked, String birthDate, String nationality, String residence, String registeredOn, String lastModifiedAt) {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public String getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(String lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
