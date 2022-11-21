import React from "react";
import { Card } from "../Ui";
import classes from "./About.module.css";
import useScreenType from "react-screentype-hook";

const About = () => {
  const screenType = useScreenType();

  return (
    <>
      <div className={classes.about_container}>
        <h2 className={classes.title}>About Us</h2>
        <p className={classes.para}>
          REMPO Ventures is a private company headquartered in Pune -
          Maharashtra. We run a portal through our mobile application which
          lists the real estate properties (Plot & Land) across India. The
          promoters are engaged in this business since last 20 years and thus
          the second generation taking ahead the legacy with the use of
          technology to the next level.{" "}
        </p>
        <p className={classes.para}>
          The purpose of running this venture is that the real estate properties
          have lesser liquidity especially in the areas beyond Tier-II cities
          which prompts the need of an active market
        </p>
        <p className={classes.para}>
          Currently we have our presence in few districts of Vidarbha,
          Marathwada and Pune region
        </p>
        <div className={classes.sm_cont}>
          <Card
            width={screenType.isMobile ? "80vw" : "50vw"}
            height="220px"
            margin="50px"
          >
            <div className={classes.card_content}>
              <h2 className={classes.number}>1000+</h2>
              <p className={classes.para2}>
                Successfully executing property transactions in very short time
                span of 12 months
              </p>
            </div>
          </Card>
        </div>
      </div>
    </>
  );
};

export default About;
