import React, { useContext } from "react";
import FormContext from "./formContenxt/formContext";
import { Input } from "../Ui";
import classes from "./Layout.module.css";
import { Button } from "../Ui";

const Form2 = ({ formNo, setFormNo }) => {
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
  const uploadFile = async (e) => {
    e.preventDefault();
    console.log("hell");
  };
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
          required={true}
        />
        <Input
          value={layoutLocation}
          onChange={(e) => {
            setLayoutLocation(e.target.value);
          }}
          placeholder="Layout Location"
          label="Location"
          required={true}
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
                required={true}
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
                  setDoc2(e.target.value);
                }}
                required={true}
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
                required={true}
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
                required={true}
              />
            </div>
          </div>
        </div>
        {formNo < 3 && (
          <Button
            onClick={() => {
              let pg = formNo;
              setFormNo(pg + 1);
              if (formNo == 2) {
                uploadFile();
              }
            }}
            type="2"
            filled
            label="Next"
            radius="4px"
          />
        )}
      </div>
    </>
  );
};

export default Form2;
