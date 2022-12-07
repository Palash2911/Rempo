import React from "react";
import classes from "./UserProfile.module.css";
import profileBanner from "../../assets/profileBanner.png";
import Card from "../Ui/Card/Card";

const UserProfile = () => {
  return (
    <>
      <div className={classes.user_page}>
        <img
          src={profileBanner}
          className={classes.background_image}
          alt="profileBanner"
        />

        <div className={classes.user_details}>
          <Card width="80vw" height="250px" radius="20px">
            <div className={classes.user_details_card}>
              <div className={classes.user_icon}>
                <i class="fas fa-user"></i>
              </div>
              <div className={classes.user_details_info}>
                <h1>Dale Carnegie</h1>
                <p>
                  <span>
                    <i class="fas fa-phone"></i>
                  </span>
                  +91 7564814659
                </p>
                <p>
                  <span>
                    <i class="fas fa-envelope"></i>
                  </span>
                  abc@gmail.com
                </p>
              </div>
              <div className={classes.edit_user_details}>
                <i class="fas fa-pen"></i>
              </div>
            </div>
          </Card>
        </div>
        <div className={classes.listings}>
          <Card width="80vw" height="250px" radius="20px">
            <div classname={classes.layout_listing}>
              <div className={classes.layout_listing_mylisting}>
                <button className={classes.hover_underline_animation}>
                  My Listings
                </button>
                <button className={classes.hover_underline_animation}>
                  Current Listings
                </button>
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
