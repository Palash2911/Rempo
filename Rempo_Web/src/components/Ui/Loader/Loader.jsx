import React from "react";
import classes from "./Loader.module.css";

const Loader = () => {
  return (
    <>
      <div className={classes.loader_container}>
        <div className={classes.loader}>
          <div className={classes.dot}></div>
          <div className={classes.dot}></div>
          <div className={classes.dot}></div>
          <div className={classes.dot}></div>
          <div className={classes.dot}></div>
          <div className={classes.dot}></div>
          <div className={classes.dot}></div>
          <div className={classes.dot}></div>
          <div className={classes.dot}></div>
        </div>
      </div>
    </>
  );
};

export default Loader;
