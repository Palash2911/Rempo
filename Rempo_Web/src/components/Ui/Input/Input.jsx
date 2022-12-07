import React from "react";
import classes from "./Input.module.css";
// import errorpng from "../../../assets/errorpng.png";

const Input = ({
  value,
  label = "def",
  reference,
  name,
  placeholder,
  type,
  onChange,
  required,
  error = "",
  width,
}) => {
  return (
    <div className={classes.group} style={{ width: width }}>
      {/* {label} */}
      <p className={classes.label}>{label}</p>
      <input
        ref={reference}
        type={type}
        value={value}
        name={name}
        className={classes.input}
        placeholder={placeholder}
        required={required}
        onChange={onChange}
      >
        {/* {label && (
          <label className={classes.form__label} htmlFor={`${name}`}>
            {label}
          </label>
        )} */}
        {/* {error && (
          <p className={classes.error}>
            <img className={classes.errorpng} src={errorpng} alt="error" />
            {error}
          </p>
        )} */}
      </input>
    </div>
  );
};

export default Input;
