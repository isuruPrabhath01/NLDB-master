package to;

import org.bytedeco.javacpp.presets.opencv_core;

public class DTOTransfer {
    private String OTP;

    public DTOTransfer(String otp2) {
        this.OTP=otp2;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
