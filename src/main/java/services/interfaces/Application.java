package services.interfaces;

import exceptions.ApplicantAlreadyAppliedException;
import model.Applicant;
import model.Store;

public interface Application {
    void apply(Applicant applicant, Store store) throws ApplicantAlreadyAppliedException;
}
