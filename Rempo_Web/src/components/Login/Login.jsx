import React, { useState } from "react";
import { Card } from "../Ui";
import classes from "./Login.module.css";
import PhoneInput from "react-phone-input-2";
import "react-phone-input-2/lib/style.css";

const Login = () => {
  const [state, setState] = useState({ phone: 0 });
  return (
    <>
      <div className={classes.container}>
        <Card width="35vw" height="70vh" radius="24px">
          <h1>Login</h1>
          <p>Phone Number</p>
          <PhoneInput
            country={"us"}
            value={state.phone}
            onChange={(phone) => setState({ phone })}
          />
          
        </Card>
      </div>
    </>
  );
};

export default Login;
