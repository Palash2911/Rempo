import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import classes from "./Navbar.module.css";

function Navbar() {
  const [click, setClick] = useState(false);

  const handleClick = () => setClick(!click);
  return (
    <>
      <nav className={classes.navbar}>
        <div className={classes.nav_container}>
          <NavLink exact to="/" className={classes.nav_logo}>
            CodeBucks
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
                to="/about"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                About
              </NavLink>
            </li>
            <li className={classes.nav_item}>
              <NavLink
                exact
                to="/blog"
                activeClassName={classes.active}
                className={classes.nav_links}
                onClick={handleClick}
              >
                Blog
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
