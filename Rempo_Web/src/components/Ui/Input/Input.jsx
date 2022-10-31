import React from "react";
import classes from "./Input.module.css";

const Input = () => {
  return (
    <div>
      <input
        placeholder="Username"
        type="text"
        className={classes.input}
        required=""
      ></input>
    </div>
  );
};

export default Input;
