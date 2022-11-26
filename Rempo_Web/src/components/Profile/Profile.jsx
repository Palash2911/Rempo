import React, { useEffect, useState } from "react";
import classes from "./Profile.module.css";
import Card from "../Ui/Card/Card";
import { Button } from "../Ui";
import { getAuth } from "firebase/auth";
import app, { db } from "../firebase_config"
import { useNavigate } from 'react-router-dom'
import useScreenType from "react-screentype-hook";
import { collection, getDocs } from "firebase/firestore";

const auth = getAuth(app);

const Profile = () => {

  let histo = useNavigate();
  const screenType = useScreenType();
  const [stateName, setStateName] = useState({ Name: '' });
  const [stateEmail, setStateEmail] = useState({ Email: '' });
  const [statePhone, setStatePhone] = useState({ Number: '' });
  const [stateDob, setStateDob] = useState({ Dob: '' });

  const handleClick = async ()=> {
    const collref = collection(db, "Users")
    getDocs(collref).then(res => {
      console.log(res)
    })
  }


  return (
    <>
      <div className={classes.profile_container}>
        <Card
            width={screenType.isMobile ? "90vw" : "35vw"}
            height="85vh"
            radius="24px"
          >  
          <form action="" className={classes.profile_form}>
            <input type="text" value={stateName.Name} name="name" placeholder="Enter Your Name" className={classes.profile_input} onChange={(e) => setStateName({ Name:e.target.value })}/>
            <input type="text" value={stateEmail.Email} name="email" placeholder="Enter Your Email" className={classes.profile_input} onChange={(e) => setStateEmail({ Email:e.target.value })}/>
            <input type="text" value={statePhone.Number} name="number" placeholder="Enter Your Phone Number" className={classes.profile_input} onChange={(e) => setStatePhone({ Phone:e.target.value })}/>
            <input type="text" name="aadhar" placeholder="1234-5678-9013" className={classes.profile_input} disabled/>
            <input type="text" value={stateDob.Dob} name="dob" placeholder="Enter Your Dob" className={classes.profile_input} onChange={(e) => setStateDob({ Dob:e.target.value })}/>
            <Button label="Create Profile" padding="0.5em 7em" radius="24px" onClick={handleClick}/>
          </form>
        </Card>
      </div>
    </>
  );
};

export default Profile;
