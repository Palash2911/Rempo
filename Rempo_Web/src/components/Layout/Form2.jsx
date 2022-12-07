import React, { useContext } from "react";
import FormContext from "./formContenxt/formContext";
import { Input } from "../Ui";
import classes from "./Layout.module.css";

const Form2 = () => {
  const {
    surveyNo,
    setSurveyNo,
    layoutLocation,
    setLayoutLocation,
    Doc1,
    setDoc1,
    Doc2,
    setDoc2,
    Doc3,
    setDoc3,
    Doc4,
    setDoc4,
  } = useContext(FormContext);
  return (
    <>
      <div className={classes.form_group}>
        <Input
          value={surveyNo}
          onChange={(e) => {
            setSurveyNo(e.target.value);
          }}
          placeholder="11/22/3 A"
          label="Survey Number"
        />
        <Input
          value={layoutLocation}
          onChange={(e) => {
            setLayoutLocation(e.target.value);
          }}
          placeholder="Layout Location"
          label="Location"
        />
        <div className={classes.form_group}>
          <h6 className={classes.file_label}>Upload Document</h6>
          <div className={classes.form_group}>
            <div className={classes.file_group}>
              <h6 className={classes.file_label}>7/12 Document</h6>
              <input
                className={classes.cutom_file_input}
                type="file"
                name="doc1"
                id=""
                value={Doc1}
                onChange={(e) => {
                  setDoc1(e.target.value);
                }}
              />
            </div>
            <div className={classes.file_group}>
              <h6 className={classes.file_label}>Nakasha</h6>
              <input
                className={classes.cutom_file_input}
                type="file"
                name="doc2"
                id=""
                value={Doc2}
                onChange={(e) => {
                  setDoc3(e.target.value);
                }}
              />
            </div>
            <div className={classes.file_group}>
              <h6 className={classes.file_label}>Na/Order</h6>
              <input
                className={classes.cutom_file_input}
                type="file"
                name="doc3"
                id=""
                value={Doc3}
                onChange={(e) => {
                  setDoc3(e.target.value);
                }}
              />
            </div>
            <div className={classes.file_group}>
              <h6 className={classes.file_label}>Other Documents</h6>
              <input
                className={classes.cutom_file_input}
                type="file"
                name="doc4"
                id=""
                value={Doc4}
                onChange={(e) => {
                  setDoc4(e.target.value);
                }}
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Form2;
