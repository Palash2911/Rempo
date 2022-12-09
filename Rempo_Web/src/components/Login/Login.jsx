import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button, Card } from "../Ui";
import classes from "./Login.module.css";
import PhoneInput from "react-phone-input-2";
import "react-phone-input-2/lib/style.css";
import useScreenType from "react-screentype-hook";
import {
  getAuth,
  signInWithPhoneNumber,
  RecaptchaVerifier,
} from "firebase/auth";
import app , { db } from "../firebase_config";
import OtpInput from "react-otp-input";
import { doc, getDoc} from "firebase/firestore";

const auth = getAuth(app);

const Login = () => {
  // let switcher = 1;
  const screenType = useScreenType();
  let histo = useNavigate();

  const onCaptchVerify = () => {
    console.log("signined");
    window.recaptchaVerifier = new RecaptchaVerifier(
      "recaptcha-container",
      {
        size: "invisible",
        callback: (response) => {
          onSignInSubmit();
          // reCAPTCHA solved, allow signInWithPhoneNumber.
        },
      },
      auth
    );
  };

  const [state, setState] = useState({ phone: 0 });
  const [otpstate, otpsetState] = useState({ otp: 0 });
  const [flagstate, setFlag] = useState({ flag: 0 });
  // To check whether the number is correct or not.
  useEffect(() => {
    console.log(state.phone);
    console.log(otpstate.otp);
  });

  const onSignInSubmit = () => {
    onCaptchVerify();
    setFlag({ flag: 1 });
    const phoneNumber = "+91" + state.phone.toString().substring(2);
    const appVerifier = window.recaptchaVerifier;
    signInWithPhoneNumber(auth, phoneNumber, appVerifier)
      .then((confirmationResult) => {
        window.confirmationResult = confirmationResult;
        // ...
      })
      .catch((error) => {
        // Error; SMS not sent
        // ...
        console.log(error.toString);
      });
  };

  const checkauth = async () => {
    const docRef = doc(db, "Users", auth.currentUser.uid);
    const docSnap = await getDoc(docRef);

    if (docSnap.exists()) {
      histo("/");
    } else {
      // doc.data() will be undefined in this case
      histo("/profile");
    }
  };

  const onVerifyCode = () => {
    window.confirmationResult
      .confirm(otpstate.otp)
      .then((result) => {
        // User signed in successfully.
        // const user = result.user;
        checkauth();
        // ...
      })
      .catch((error) => {
        // User couldn't sign in (bad verification code?)
        // ...
      });
  };

  return (
    <>
      <div className={classes.container}>
        <div id="recaptcha-container"></div>
        {flagstate.flag === 0 ? (
          <Card
            width={screenType.isMobile ? "90vw" : "35vw"}
            height="70vh"
            radius="24px"
          >
            <div className={classes.form_container}>
              <h1>Login</h1>
              <div className={classes.outerContainer}>
                <p>Phone Number</p>
                <div className={classes.PhoneInputContainer}>
                  <PhoneInput
                    searchPlaceholder="search"
                    country={"in"}
                    value={state.phone}
                    onChange={(phone) => setState({ phone })}
                  />
                </div>
              </div>
              <Button
                label="Send OTP"
                padding="0.5em 7em"
                radius="24px"
                onClick={onSignInSubmit}
              />
            </div>
          </Card>
        ) : (
          <Card
            width={screenType.isMobile ? "90vw" : "35vw"}
            height="70vh"
            radius="24px"
          >
            <div className={classes.form_container}>
              <h1>Login</h1>
              <div className={classes.outerContainer}>
                <p className={classes.otptext}>OTP</p>
                <div className={classes.PhoneInputContainer}>
                  <OtpInput
                    value={otpstate.otp}
                    onChange={(otp) => otpsetState({ otp })}
                    numInputs={6}
                    separator={<span>-</span>}
                    inputStyle={{
                      width: "2.5rem ",
                      height: "2.5rem",
                      margin: "0 0.5rem",
                      fontSize: "2rem",
                      borderRadius: "4px",
                      border: "1px solid rgba(0, 0, 0, 0.3)",
                    }}
                  />
                </div>
              </div>
              <Button
                label="Verify OTP"
                padding="0.5em 7em"
                radius="24px"
                onClick={onVerifyCode}
              />
            </div>
          </Card>
        )}
      </div>
    </>
  );
};

export default Login;
