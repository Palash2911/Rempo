import React from "react";
import classes from "./Landing.module.css";
import landing from "../../assets/landing.png";
import Card from "../Ui/Card/Card";

const Landing = () => {
  return (
    <>
      <div className={classes.landing_container}>
        <img className={classes.image} src={landing} alt="landing" />
        <div className={classes.main_div}>
          <Card width="50vw" height="200px" radius="15px" />
        </div>
      </div>
    </>
  );
};

export default Landing;
