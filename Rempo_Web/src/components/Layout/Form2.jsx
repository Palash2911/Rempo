import React from "react";
import { Input } from "../Ui";
import classes from "./Layout.module.css";

const Form2 = () => {
  return (
    <>
      <div className={classes.form_group}>
        <Input placeholder="11/22/3 A" label="Survey Number" />
        <Input placeholder="Layout Location" label="Location" />
        <div className={classes.form_group}>
          <h6 className={classes.file_label}>Upload Document</h6>
          <div className={classes.form_group}>
            <div className={classes.file_group}>
              <h6 className={classes.file_label}>7/12 Document</h6>
              <input
                className={classes.cutom_file_input}
                type="file"
                name=""
                id=""
              />
            </div>
            <div className={classes.file_group}>
              <h6 className={classes.file_label}>Nakasha</h6>
              <input
                className={classes.cutom_file_input}
                type="file"
                name=""
                id=""
              />
            </div>
            <div className={classes.file_group}>
              <h6 className={classes.file_label}>Na/Order</h6>
              <input
                className={classes.cutom_file_input}
                type="file"
                name=""
                id=""
              />
            </div>
            <div className={classes.file_group}>
              <h6 className={classes.file_label}>Other Documents</h6>
              <input
                className={classes.cutom_file_input}
                type="file"
                name=""
                id=""
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Form2;
