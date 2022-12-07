import React, { useState } from "react";
import classes from "./Layout.module.css";
import { Button, Card } from "../Ui";
import Form1 from "./Form1";
import Form2 from "./Form2";
import Form3 from "./Form3";
import useScreenType from "react-screentype-hook";

const Layout = () => {
  const screenType = useScreenType();
  const [formNo, setFormNo] = useState(1);

  return (
    <>
      <div className={classes.form_container}>
        <Card
          width={screenType.isMobile ? "90vw" : "70vw"}
          height="auto"
          radius="24px"
        >
          <div className={classes.form_inner_container}>
            {formNo === 3 ? <h1>Plot Details</h1> : <h1>Layout Details</h1>}
            {formNo === 1 ? <Form1 /> : formNo === 2 ? <Form2 /> : <Form3 />}
            <div className={classes.btn_containers}>
              {formNo > 1 && (
                <Button
                  onClick={() => {
                    let pg = formNo;
                    setFormNo(pg - 1);
                  }}
                  type="2"
                  filled
                  label="Back"
                  radius="4px"
                />
              )}
              {formNo < 3 && (
                <Button
                  onClick={() => {
                    let pg = formNo;
                    setFormNo(pg + 1);
                  }}
                  type="2"
                  filled
                  label="Next"
                  radius="4px"
                />
              )}
            </div>
          </div>
        </Card>
      </div>
    </>
  );
};

export default Layout;
