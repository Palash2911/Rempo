import React from "react";
import classes from "./Team.module.css";
import { Card } from "../Ui";
import { teamsData } from "../../assets/teams";
import useScreenType from "react-screentype-hook";

const Team = () => {
  const screenType = useScreenType();

  return (
    <>
      <div className={classes.team_container}>
        <h2 className={classes.heading}>Our Team</h2>
        <p className={classes.para}>
          Our goal is at the heart of all that we do. We make our clients
          happiness our sole priority.
        </p>
        <div className={classes.cards}>
          {teamsData.map((c, i) => {
            return (
              <Card
                key={i}
                width={screenType.isMobile ? "80vw" : "35vw"}
                height="40vh"
                margin="5vw 2vw"
                radius="10px"
              >
                <div className={classes.card_data}>
                  <img
                    className={classes.teams_img}
                    src={c.logo}
                    alt="milestone"
                  />
                  <p className={classes.teams_title}>{c.title}</p>
                </div>
              </Card>
            );
          })}
        </div>
      </div>
    </>
  );
};

export default Team;
