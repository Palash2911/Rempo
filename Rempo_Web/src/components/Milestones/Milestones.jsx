import React from "react";
import classes from "./Milestones.module.css";
import { Card } from "../Ui";
import { milestoneData } from "../../assets/milestones";

const Milestones = () => {
  return (
    <>
      <div className={classes.milestone_container}>
        <h2 className={classes.heading}>Our Milestones</h2>
        <p className={classes.para}>
          Our goal is at the heart of all that we do. We make our clients
          happiness our sole priority.
        </p>
        <div className={classes.cards}>
          {milestoneData.map((c, i) => {
            return (
              <Card
                key={i}
                width="300px"
                height="150px"
                margin="50px"
                radius="10px"
              >
                <div className={classes.card_data}>
                  <img
                    className={classes.miles_img}
                    src={c.logo}
                    alt="milestone"
                  />
                  <p className={classes.miles_title}>{c.title}</p>
                </div>
              </Card>
            );
          })}
        </div>
      </div>
    </>
  );
};

export default Milestones;
