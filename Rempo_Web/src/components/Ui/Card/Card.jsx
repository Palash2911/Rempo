import React from "react";
import classes from "./Card.module.css";

const Card = ({ width, height, radius, children, margin }) => {
  return (
    <div>
      <div
        style={{
          width: width,
          height: height,
          borderRadius: radius,
          margin: margin,
        }}
        className={classes.card}
      >
        {children}
      </div>
    </div>
  );
};

export default Card;
