import React from "react";
import classes from "./Loader.module.css";

const Loader = () => {
  return (
    <>
      <div className={classes.loader_container}>
        <div className={classes.loader}>
          {[1, 2, 3, 4, 5, 6, 7, 8, 9].map(() => {
            return <div className={classes.dot}></div>;
          })}
        </div>
      </div>
    </>
  );
};

export default Loader;
