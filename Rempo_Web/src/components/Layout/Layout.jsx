import React, { useState, useContext } from "react";
import classes from "./Layout.module.css";
import { Button, Card } from "../Ui";
import Form1 from "./Form1";
import Form2 from "./Form2";
import Form3 from "./Form3";
import useScreenType from "react-screentype-hook";
import { getAuth, onAuthStateChanged } from "firebase/auth";
import app, { db } from "../firebase_config";
import { collection, doc, setDoc, getDoc, addDoc } from "firebase/firestore";
import FormContext from "./formContenxt/formContext";

const auth = getAuth(app);

const Layout = () => {
  const screenType = useScreenType();
  const [formNo, setFormNo] = useState(1);
  const {
    owner,
    setOwner,
    category,
    setCategory,
    desc,
    setDesc,
    district,
    setDistrict,
    taluka,
    setTaluka,
    village,
    setVillage,
  } = useContext(FormContext);
  const handleClick = async (e) => {
    e.preventDefault();
    try {
      // const docid = await addDoc(collection(db, "Layouts"), {
      //   address: district,
      //   availabelPlots: 2,
      //   district: district,
      //   layoutId: "adsf",
      //   location: "1234-5678-9000",
      //   sellerId: auth.currentUser.uid,
      //   sellerName: owner,
      //   soldPlots: 1,
      //   State: "Goa",
      //   taluka: taluka,
      //   title: "TITLE",
      //   totalPlots: 3,
      // });
      // console.log("asdf", docid.id);
    } catch (e) {
      console.log("Error", e);
      alert(e);
    }
  };
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
              {formNo === 3 && (
                <Button label="Submit" type="1" onClick={handleClick} />
              )}
            </div>
          </div>
        </Card>
      </div>
    </>
  );
};

export default Layout;
