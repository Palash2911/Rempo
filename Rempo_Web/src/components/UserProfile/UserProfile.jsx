import React, { useEffect , useState} from "react";
import classes from "./UserProfile.module.css";
import profileBanner from "../../assets/profileBanner.png";
import Card from "../Ui/Card/Card";
import { collection, doc, setDoc, getDoc } from "firebase/firestore";
import app, { db } from "../firebase_config";
import { getAuth, onAuthStateChanged } from "firebase/auth";

const auth = getAuth(app);

const UserProfile = () => {
  
  const [stateName, setStateName] = useState({ Name: "Daniel" });
  const [stateEmail, setStateEmail] = useState({ Email: "abc@gmail.com" });
  const [statePhone, setStatePhone] = useState({ Number: "+91 720115324" });
  
  const fillauth = async () => {
    const docRef = doc(db, "Users", auth.currentUser.uid);
    const docSnap = await getDoc(docRef);

    if (docSnap.exists()) {
      setStatePhone({Number: docSnap.data().Phone})
      setStateName({Name: docSnap.data().Name})
      setStateEmail({Email: docSnap.data().Email})
    } else {
      // doc.data() will be undefined in this case
      histo("/profile");
    }
  };

  useEffect(() => {
    fillauth()
    //eslint-disable-next-line
  }, []);

  useEffect(() => {
    
    //eslint-disable-next-line
  }, []);
  
  return (
    <>
      <div className={classes.user_page}>
        <img src={profileBanner} className={classes.background_image} alt="profileBanner" />
        <div className={classes.user_details}>
          <Card width="80vw" height="250px" radius="20px">
            <div className={classes.user_details_card}>
              <div className={classes.user_icon}>
                <i class="fas fa-user"></i> 
              </div>
              <div className={classes.user_details_info} >
                <h1>{stateName.Name}</h1>
                <p>
                  <span>
                    <i class="fas fa-phone"></i>
                  </span>
                  {statePhone.Number}
                </p>
                <p>
                  <span>
                    <i class="fas fa-envelope"></i>
                  </span>
                  {stateEmail.Email}
                </p>
              </div>
              <div className={classes.edit_user_details}>
                <i class="fas fa-pen"></i>
              </div>
            </div>
          </Card>
          <Card width="80vw" height="250px" radius="20px">
            <div classname={classes.layout_listing}>
              <div className={classes.layout_listing_mylisting}>
                <button className={classes.hover_underline_animation}>My Listings</button>
                <button className={classes.hover_underline_animation}>Current Listings</button>
              </div>
              <ul className={classes.layout_listing_details}>
                <li>Layout 1</li>
                <li>Layout 2</li>
                <li>Layout 3</li>
                <li>Layout 4</li>
              </ul>
            </div>
          </Card>
        </div>
      </div>
    </>
  );
};

export default UserProfile;
