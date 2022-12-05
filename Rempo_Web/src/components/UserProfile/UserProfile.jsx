import React from "react";
import classes from "./UserProfile.module.css";
import profileBanner from "../../assets/profileBanner.png";
import Card from "../Ui/Card/Card";

const UserProfile = () => {
  return (
    <>
      <div>
        <img src={profileBanner} alt="profileBanner" />
        <div>
          <Card width="80%" height="250px" radius="20px">
            <div>
              <div>
                <i class="fas fa-user"></i>
              </div>
              <div>
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
              <div>
                <i class="fas fa-pen"></i>
              </div>
            </div>
          </Card>
          <Card width="80%" height="250px" radius="20px">
            <div>
              <div>
                <button>My Listings</button>
                <button>Current Listings</button>
              </div>
              <ul>
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
