package com.patika.atmProject.app.general.exception.exceptionEnums;

public enum GeneralErrorMessage implements BaseErrorMessage{
    INTERNAL_SERVER_ERROR("Encounter internal server", "Server encountered an unexpected condition that prevented it from fulfilling the request"),
    TC_ALREADY_USED("Tc was used already!","Cannot register the same tc that was used by someone."),
    BANK_NAME_ALREADY_USED("This bank name already used!", "This bank name was taken! It should be unique!"),
    PHONE_NUMBER_ALREADY_USED("This phone number used already!", "Cannot register the same phone number that was used by someone."),
    TC_LENGTH_ERROR("Length of tc error!", "Length of the written Tc must be 11."),
    IBAN_LENGTH_ERROR("Length of iban error!", "Length of iban must be 26 with TR."),
    PHONE_NUMBER_LENGTH_ERROR("Length of phone number error!","Length of the written phone number must be 11!"),
    INCLUDE_ONLY_LETTER_ERROR("Includes different things from lettter!", "Name or surname must be included only letters."),
    INCLUDE_ONLY_NUMBER_ERROR("Includes different things from number", "Phone number must include only number!"),
    IBAN_UNIQUE_ERROR("Iban no not unique!", "This iban no was given to another customer, so it cannot be used"),
    NOT_AVAILABLE_AMOUNT("Amount is not available!", "Amount must be bigger than zero."),
    NOT_ENOUGH_AMOUNT("Amount is not enough!", "For keeping there should be enough money in amaount."),
    IBAN_NOT_FOUND("This iban not found!", "Use an existing iban no."),
    ACCOUNT_STATUS_ERROR("Account status error!", "Account must be active."),
    ;

    private String errorMessage;
    private String messageDetail;

    GeneralErrorMessage(String errorMessage, String messageDetail){
        this.errorMessage = errorMessage;
        this.messageDetail = messageDetail;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    @Override
    public String getDetailMessage() {
        return this.messageDetail;
    }
}
