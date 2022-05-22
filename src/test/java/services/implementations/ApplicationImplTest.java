package services.implementations;

import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.ApplicantAlreadyAppliedException;
import model.Applicant;
import model.Staff;
import model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationImplTest {
    ApplicationImpl applicationImpl;
    Store company;
    Staff staff;
    Applicant applicant1;
    Applicant applicant2;
    Applicant applicant3;
    Applicant applicant4;



    @BeforeEach
    void setUp() {
        applicationImpl = new ApplicationImpl();
        company = new Store();
        applicant1 = new Applicant(001, "Desmond Dameh", Gender.MALE, "desmonddam@mail.globe", Role.APPLICANT, Qualification.HND);
        applicant2 = new Applicant(002, "Abel June", Gender.FEMALE, "abeljune@mail.globe", Role.APPLICANT, Qualification.ND);
        applicant3 = new Applicant(003, "Duru Gal", Gender.MALE, "durugal@mail.globe", Role.APPLICANT, Qualification.ND);
        applicant4 = new Applicant(004, "Brooks Gideon", Gender.MALE, "brooksg@mail.globe", Role.APPLICANT, Qualification.MSC);

    }

    @Test
    public void applicantListShouldBeUpdatedAfterASuccessfulApplication() throws ApplicantAlreadyAppliedException {
        applicationImpl.apply(applicant1, company);
        applicationImpl.apply(applicant2, company);
        applicationImpl.apply(applicant3, company);
        applicationImpl.apply(applicant4, company);
        assertEquals(4, company.getApplicants().size());
    }

    @Test
    public void ShouldThrowAnExceptionWhenApplicantsApplicationAlreadyExist() throws ApplicantAlreadyAppliedException {
        applicationImpl.apply(applicant1, company);
        assertThrows(ApplicantAlreadyAppliedException.class, ()->
                applicationImpl.apply(applicant1, company));
    }
}