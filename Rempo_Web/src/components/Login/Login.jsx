import React, { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom'
import { Button, Card } from "../Ui";
import classes from "./Login.module.css";
import PhoneInput from "react-phone-input-2";
import "react-phone-input-2/lib/style.css";
import useScreenType from "react-screentype-hook";
import { getAuth, signInWithPhoneNumber, RecaptchaVerifier } from "firebase/auth";
import app from "../firebase_config"


const auth = getAuth(app);

const Login = () => {
  let switcher = 1;
  const screenType = useScreenType();
  let histo = useNavigate();

  const onCaptchVerify=() =>{
    console.log("signined")
    window.recaptchaVerifier = new RecaptchaVerifier
    ("recaptcha-container", 
    {
      size: "invisible",
      callback: (response) => {
        onSignInSubmit()
    // reCAPTCHA solved, allow signInWithPhoneNumber.
    },
  }, 
  auth);
  }

  const [state, setState] = useState({ phone: 0 });
  const [otpstate, otpsetState] = useState({ otp: '' });
  const [flagstate, setFlag] = useState({flag: 0});
  // To check whether the number is correct or not.
  useEffect(() => {
    console.log(state.phone);
    console.log(otpstate.otp);
  });

  const onSignInSubmit=() =>  {
    onCaptchVerify();
    setFlag({flag: 1})
    const phoneNumber = "+91" + state.phone.toString().substring(2); 
    const appVerifier = window.recaptchaVerifier;
    signInWithPhoneNumber(auth, phoneNumber, appVerifier)
    .then((confirmationResult) => {
      window.confirmationResult = confirmationResult;
      // ...
    }).catch((error) => {
      // Error; SMS not sent
      // ...
      console.log(error.toString)
    });
  }

  const onVerifyCode=() =>{
    window.confirmationResult.confirm(otpstate.otp).then((result) => {
      // User signed in successfully.
      const user = result.user;
      histo("/profile")
      // ...
    }).catch((error) => {
      // User couldn't sign in (bad verification code?)
      // ...
    });
  }

  return (
    <>
      <div className={classes.container}>
      <div id="recaptcha-container"></div>
       {flagstate.flag==0 ? <Card
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
            <Button label="Send OTP" padding="0.5em 7em" radius="24px" onClick={onSignInSubmit} />
          </div>
        </Card>: 
        <Card
        width={screenType.isMobile ? "90vw" : "35vw"}
        height="70vh"
        radius="24px"
      >
        <div className={classes.form_container}>
          <h1>Login</h1>
          <div className={classes.outerContainer}>
            <p>OTP</p>
            <div className={classes.PhoneInputContainer}>
              <input type="number" value={otpstate.otp} onChange={(e) => otpsetState({ otp:e.target.value })} />
            </div>
          </div>
          <Button label="Verify OTP" padding="0.5em 7em" radius="24px" onClick={onVerifyCode}/>
        </div>
      </Card>}
      </div>
    </>
  );
};

export default Login;
