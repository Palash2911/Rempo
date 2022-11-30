import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import classes from "./Navbar.module.css";
import Logo from "../Ui/Logo/Logo";
import { getAuth, signOut } from "firebase/auth";
import app, { db } from "../firebase_config"

const auth = getAuth(app);

function Navbar() {
  const [click, setClick] = useState(false);

  const handleClick = () => {
    setClick(!click)
    signOut(auth);
  };
    
  return (
    <>
      <nav className={classes.navbar}>
        <div className={classes.nav_container}>
          <NavLink exact to="/" className={classes.nav_logo}>
            <Logo />
          </NavLink>
          <ul
            className={
              click
                ? `${classes.nav_menu} ${classes.active}`
                : `${classes.nav_menu}`
            }
          >
            <li className={classes.nav_item}>
              <NavLink
                exact
                to="/"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                Home
              </NavLink>
            </li>
            <li className={classes.nav_item}>
              <NavLink
                exact
                to="/milestones"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                Milestones
              </NavLink>
            </li>
            <li className={classes.nav_item}>
              <NavLink
                exact
                to="/services"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                Services
              </NavLink>
            </li>
            <li className={classes.nav_item}>
              <NavLink
                exact
                to="/team"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                Our Team
              </NavLink>
            </li>
            <li className={classes.nav_item}>
              <NavLink
                exact
                to="/about"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                About Us
              </NavLink>
            </li>
            <li className={classes.nav_item}>
              <NavLink
                exact
                to="/contact"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                Contact Us
              </NavLink>
            </li>
            <li className={classes.nav_item}>
              <NavLink
                exact
                to="/profile"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                <i class="fas fa-user"></i>
              </NavLink>
            </li>
          </ul>
          <div className={classes.nav_icon} onClick={handleClick}>
            <i className={click ? "fas fa-times" : "fas fa-bars"}></i>
          </div>
        </div>
      </nav>
    </>
  );
}

export default Navbar;
