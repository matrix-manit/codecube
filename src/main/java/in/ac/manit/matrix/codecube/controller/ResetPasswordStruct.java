package in.ac.manit.matrix.codecube.controller;

/**
 * Created by tanay khemani on 13-Mar-17.
 */


public class ResetPasswordStruct {
    private Long scholarNumber;
    private String otp;
    private String newPassword;

    public Long getScholarNumber() {

        return scholarNumber;
    }

    public void setScholarNumber(Long scholarNumber) {
        this.scholarNumber = scholarNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
