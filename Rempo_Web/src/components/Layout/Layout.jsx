import React, { useState } from "react";
import classes from "./Layout.module.css";
import { Card } from "../Ui";
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
          width={screenType.isMobile ? "90vw" : "50vw"}
          height="110vh"
          radius="24px"
        >
          <div className={classes.form_inner_container}>
            <h1>Layout Details</h1>
            {formNo === 1 ? <Form1 /> : formNo === 2 ? <Form2 /> : <Form3 />}
            <div>
              {formNo > 1 && (
                <button
                  className="btn btn-primary"
                  type="button"
                  onClick={() => {
                    let pg = formNo;
                    setFormNo(pg - 1);
                  }}
                >
                  Back
                </button>
              )}
              {formNo < 3 && (
                <button
                  className="btn btn-primary mx-4"
                  type="button"
                  onClick={() => {
                    let pg = formNo;
                    setFormNo(pg + 1);
                  }}
                >
                  Next
                </button>
              )}
            </div>
          </div>
        </Card>
      </div>
    </>
  );
};

export default Layout;
