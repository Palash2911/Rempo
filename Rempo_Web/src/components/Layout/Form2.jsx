import React, { useContext } from "react";
import FormContext from "./formContenxt/formContext";
import { Input } from "../Ui";
import classes from "./Layout.module.css";
import { Button } from "../Ui";
import {
  ref,
  uploadBytes,
  getDownloadURL,
} from "firebase/storage";
import  app, { db } from "../firebase_config"
import { getAuth } from "firebase/auth";
import { getStorage } from "firebase/storage";
import { collection, doc, setDoc, addDoc } from "firebase/firestore";

const auth = getAuth(app);
const storage = getStorage(app);

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
    Doc1url, 
    setDoc1url,
    Doc2url, 
    setDoc2url,
    Doc3url, 
    setDoc3url,
    Doc4url, 
    setDoc4url,
  } = useContext(FormContext);

  const uploadFile = async (e) => {
    e.preventDefault();
    const docListRef = ref(storage, "Documents/"+auth.currentUser.uid+"_doc1_"+surveyNo);
    if(!surveyNo || !layoutLocation || !Doc1)
    {
        alert("Please Enter All Details")
    }
    else
    { 
        if(Doc1!=null)
        {
          const upf = uploadBytes(docListRef, Doc1)
          upf.then((snapshot) => {
            getDownloadURL(snapshot.ref).then((url)=>{
              setDoc1url((e)=>url)
              console.log(Doc1url)
            })
          })
        }
        let pg = formNo;
        setFormNo(pg + 1);
    }
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
                onChange={(e) => {
                  setDoc1(e.target.files[0]);
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
                onChange={(e) => {
                  setDoc2(e.target.files[0]);
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
                onChange={(e) => {
                  setDoc3(e.target.files[0]);
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
                onChange={(e) => {
                  setDoc4(e.target.files[0]);
                }}
                required={true}
              />
            </div>
          </div>
        </div>
        {formNo < 3 && (
          <Button
            onClick={uploadFile}
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
