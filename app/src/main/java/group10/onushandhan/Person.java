package group10.onushandhan;

import android.net.Uri;

/**
 * Created by Promit on 9/19/2017.
 */

public class Person  {
    String PersonId, etNameOfMissingPerson, etAgeOfMissingPerson, etHeightOfMissingPerson;
    String etSkinOfMissingPerson, etHairOfMissingPerson, etMissingPlace;
    String etMissingDate, etMark, etContactNumber, etHomeAddress;
    String imageId;
    String imageLink;

    public Person() {

    }

    public Person(String PersonId, String etNameOfMissingPerson, String etAgeOfMissingPerson, String etHeightOfMissingPerson, String etSkinOfMissingPerson, String etHairOfMissingPerson, String etMissingPlace, String etMissingDate, String etMark, String etContactNumber, String etHomeAddress, String imageId, String imageLink) {
        this.PersonId = PersonId;
        this.etNameOfMissingPerson = etNameOfMissingPerson;
        this.etAgeOfMissingPerson = etAgeOfMissingPerson;
        this.etHeightOfMissingPerson = etHeightOfMissingPerson;
        this.etSkinOfMissingPerson = etSkinOfMissingPerson;
        this.etHairOfMissingPerson = etHairOfMissingPerson;
        this.etMissingPlace = etMissingPlace;
        this.etMissingDate = etMissingDate;
        this.etMark = etMark;
        this.etContactNumber = etContactNumber;
        this.etHomeAddress = etHomeAddress;
        this.imageId = imageId;
        this.imageLink = imageLink;
    }

    public String getEtNameOfMissingPerson() {
        return etNameOfMissingPerson;
    }

    public String getEtAgeOfMissingPerson() {
        return etAgeOfMissingPerson;
    }

    public String getEtHeightOfMissingPerson() {
        return etHeightOfMissingPerson;
    }

    public String getEtSkinOfMissingPerson() {
        return etSkinOfMissingPerson;
    }

    public String getEtHairOfMissingPerson() {
        return etHairOfMissingPerson;
    }

    public String getEtMissingPlace() {
        return etMissingPlace;
    }

    public String getEtMissingDate() {
        return etMissingDate;
    }

    public String getEtMark() {
        return etMark;
    }

    public String getEtContactNumber() {
        return etContactNumber;
    }

    public String getEtHomeAddress() {
        return etHomeAddress;
    }

    public String getImageId() {
        return imageId;
    }

    public String getImageLink() {
        return imageLink;
    }
}
