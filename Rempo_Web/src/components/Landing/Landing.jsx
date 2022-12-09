import React from "react";
import classes from "./Landing.module.css";
import landing from "../../assets/landing.png";
import Card from "../Ui/Card/Card";
import { Button } from "../Ui";
import { useNavigate } from "react-router-dom";
import app  from "../firebase_config";
import { getAuth } from "firebase/auth";

const auth = getAuth(app);

const Landing = () => {
  let histo = useNavigate();
  const handleClick = async () =>{
    if(auth.currentUser==null)
    {
      histo("/login")
    }
    else
    {
      histo("/layout")
    }
  }
  return (
    <>
      <div className={classes.landing_container}>
        <img className={classes.image} src={landing} alt="landing" />
        <div className={classes.main_div}>
          <Card width="50vw" height="200px" radius="15px">
            <div className={classes.btn_container}>
              <Button type="1" label="List a Plot"></Button>
              <Button type="2" filled label="List a Layout" onClick={handleClick}/>
            </div>
          </Card>
        </div>
      </div>
    </>
  );
};

export default Landing;
