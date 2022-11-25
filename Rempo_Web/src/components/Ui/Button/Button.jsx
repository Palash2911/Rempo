import React from "react";
import classes from "./Button.module.css";

const Button = ({
  children,
  onClick,
  label = "Label Missing",
  disabled,
  filled,
  padding = "0.5em 1.5em",
  fontSize = "17px",
  type = "0",
  radius,
}) => {
  return (
    <>
      {type === "1" ? (
        <button
          style={{ padding: padding, fontSize: fontSize, borderRadius: radius }}
          className={filled ? classes.fill : classes.btn}
          onClick={onClick}
          disabled={disabled}
        >
          {label || children}
        </button>
      ) : (
        <button
          style={{ padding: padding, fontSize: fontSize, borderRadius: radius }}
          className={classes.dim}
          onClick={onClick}
          disabled={disabled}
        >
          {label || children}
        </button>
      )}
    </>
  );
};

export default Button;
