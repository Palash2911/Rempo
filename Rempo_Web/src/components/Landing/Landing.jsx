import React from "react";
import classes from "./Landing.module.css";
import landing from "../../assets/landing.png";
import Card from "../Ui/Card/Card";
import { Button } from "../Ui";
import { Link } from "react-router-dom";

const Landing = () => {
  return (
    <>
      <div className={classes.landing_container}>
        <img className={classes.image} src={landing} alt="landing" />
        <div className={classes.main_div}>
          <Card width="50vw" height="200px" radius="15px">
            <div className={classes.btn_container}>
              <Button type="1" label="List a Plot"></Button>
              <Link to="/layout">
                <Button type="2" filled label="List a Layout" />
              </Link>
            </div>
          </Card>
        </div>
      </div>
    </>
  );
};

export default Landing;
