package com.krakozhia.visa.visaApplication.domain.repository;

import com.krakozhia.visa.common.exception.DomainException;
import com.krakozhia.visa.visaApplication.domain.info.PassportInformation;
import com.krakozhia.visa.visaApplication.domain.model.*;

import java.util.Optional;

public interface VisaApplicationRepository {
    Long generateId();
    Optional<VisaFeeReceipt> getVisaFeeReceipt(Long paymentId);
    Optional<Applicant> getApplicant(Long applicantId);

    Optional<PassportInformation> getPassportInfo(Long applicantId);

    boolean isCountryEligibleForVisa(String countryCode);

    Optional<Address> getAddress(Long applicantId);

    void save(VisaApplication visaApplication);

    VisaApplication retrieveVisaApplicationById(VisaApplicationId visaApplicationId) throws DomainException;

    void send(VisaApplication visaApplication);
}