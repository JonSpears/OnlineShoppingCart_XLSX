package services.implementations;

import enums.Role;
import exceptions.ApplicantAlreadyAppliedException;
import model.Applicant;
import model.Store;
import services.interfaces.Application;


public class ApplicationImpl implements Application {
    @Override
    public void apply(Applicant applicant, Store store) throws ApplicantAlreadyAppliedException {
        if(store.getApplicants().contains(applicant)){
            throw new ApplicantAlreadyAppliedException("You have already applied for this job.");
        }
        store.getApplicants().add(applicant);
        System.out.println(applicant.getUserName() + " has successfully applied for the role of "
                + Role.CASHIER + " at " + store.getCompanyName());
    }

}
