import React, { useEffect, useState } from "react";
import { Button, Card } from "../Ui";
import classes from "./Login.module.css";
import PhoneInput from "react-phone-input-2";
import "react-phone-input-2/lib/style.css";
import useScreenType from "react-screentype-hook";

const Login = () => {
  let switcher = 1;
  const screenType = useScreenType();

  const [state, setState] = useState({ phone: 0 });
  // To check whether the number is correct or not.
  useEffect(() => {
    console.log(state.phone);
  });
  return (
    <>
      <div className={classes.container}>
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
            <Button label="Send OTP" padding="0.5em 7em" radius="24px" />
            <p>
              Don&apos;t have an account ? <span>Create Account</span>
            </p>
          </div>
        </Card>
      </div>
    </>
  );
};

export default Login;
