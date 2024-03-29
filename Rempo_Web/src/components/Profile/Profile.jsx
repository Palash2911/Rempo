import React, { useState } from "react";
import classes from "./Profile.module.css";
import Card from "../Ui/Card/Card";
import { Button } from "../Ui";
import { getAuth } from "firebase/auth";
import app, { db } from "../firebase_config";
import { useNavigate } from "react-router-dom";
import useScreenType from "react-screentype-hook";
import { collection, doc, setDoc } from "firebase/firestore";
import Profile_section from "../../assets/profile_section_pic.png";
// import Input from "../Ui/Input/Input";

const auth = getAuth(app);

const Profile = () => {
  let histo = useNavigate();
  const screenType = useScreenType();
  const [stateName, setStateName] = useState({ Namee: "" });
  const [stateEmail, setStateEmail] = useState({ Email: "" });
  const [statePhone, setStatePhone] = useState({ Number: "" });
  const [stateDob, setStateDob] = useState({ Dob: "" });

  const handleClick = async (e) => {
    e.preventDefault();
    try {
      const dr = collection(db, "Users");

      await setDoc(doc(dr, auth.currentUser.uid.toString()), {
        Name: stateName.Namee,
        Email: stateEmail.Email,
        Phone: statePhone.Number,
        dob: stateDob.Dob,
        Aadhar: "1234-5678-9000",
        Account: "Seller",
        Filter: "",
        Uid: auth.currentUser.uid,
        Verified: true,
        profilePicture: "",
      });

      histo("/");
    } catch (e) {
      console.log("Error", e);
      alert(e);
    }
  };

  return (
    <>
      <div className={classes.profile_container}>
        <Card
          width={!screenType.isMobile ? "79vw" : "35vw"}
          height="85vh"
          radius="24px"
        >
          <div className={classes.profile_card}>
            <div className={classes.profile_left}>
              <div className={classes.profile_left_inner}></div>
              <div className={classes.profile_left_text}>
                <h2 className={classes.profile_left_heading}>rempo</h2>
                <div className={classes.profile_left_suinner}>
                  <h2>1000+ Agents</h2>
                  <h4 style={{ fontWeight: "400" }}>
                    Connecting Local Market with Technology
                  </h4>
                </div>
              </div>
              <img
                className={classes.profile_img}
                src={Profile_section}
                alt="profile_left_picture"
              />
            </div>
            <div className={classes.profile_right}>
              <h1>Create Your Account</h1>
              <form action="" className={classes.profile_form}>
                <div className={classes.profile_input_div}>
                  <label>Full Name: </label>
                  <input
                    type="text"
                    value={stateName.Namee}
                    name="name"
                    placeholder="John Smith"
                    className={classes.profile_input}
                    onChange={(e) => setStateName({ Namee: e.target.value })}
                  />
                </div>
                <div className={classes.profile_input_div}>
                  <label>Email: </label>
                  <input
                    type="text"
                    value={stateEmail.Email}
                    name="email"
                    placeholder="xyz@gmail.com"
                    className={classes.profile_input}
                    onChange={(e) => setStateEmail({ Email: e.target.value })}
                  />
                </div>
                <div className={classes.profile_input_div}>
                  <label>Phone Number: </label>
                  <input
                    type="text"
                    value={statePhone.Number}
                    name="number"
                    placeholder="+91 1234567890"
                    className={classes.profile_input}
                    onChange={(e) => setStatePhone({ Number: e.target.value })}
                  />
                </div>
                <div className={classes.profile_input_div}>
                  <label>Date Of Birth: </label>
                  <input
                    type="text"
                    value={stateDob.Dob}
                    name="dob"
                    placeholder="12/11/2002"
                    className={classes.profile_input}
                    onChange={(e) => setStateDob({ Dob: e.target.value })}
                  />
                </div>
                <div className={classes.profile_input_div}>
                  <label>Aadhar Number: </label>
                  <input
                    type="text"
                    name="aadhar"
                    placeholder="1234-5678-9013"
                    className={classes.profile_input}
                    disabled
                  />
                </div>
                <Button
                  label="Create Profile"
                  padding="0.5em 7em"
                  radius="24px"
                  onClick={handleClick}
                />
              </form>
            </div>
          </div>
        </Card>
      </div>
    </>
  );
};

export default Profile;
