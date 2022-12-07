import React, { useState, useContext  } from "react";
import classes from "./Layout.module.css";
import { Button, Card, Input } from "../Ui";
import useScreenType from "react-screentype-hook";
import { getAuth, onAuthStateChanged } from "firebase/auth";
import app, { db } from "../firebase_config";
import { collection, doc, setDoc, getDoc, addDoc } from "firebase/firestore";
import FormContext from "./formContenxt/formContext";

const auth = getAuth(app);

const inputArr = [
  {
    type: "text",
    id: 1,
    value: "",
  },
];

const Form3 = () => {
  const [arr, setArr] = useState(inputArr);
  const screenType = useScreenType();

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
        const docid = await addDoc(collection(db, "Layouts"), {
          address: district,
          availabelPlots: 2,
          district: district,
          layoutId: "adsf",
          location: "1234-5678-9000",
          sellerId: auth.currentUser.uid,
          sellerName: owner,
          soldPlots: 1,
          State: "Goa",
          taluka: taluka,
          title: "TITLE",
          totalPlots: 3,
        });
        console.log("asdf", docid.id)
      } catch (e) {
        console.log("Error", e);
        alert(e);
      }
  };

  const addInput = () => {
    setArr((s) => {
      return [
        ...s,
        {
          type: "text",
          value: "",
        },
      ];
    });
  };

  const handleChange = (e) => {
    e.preventDefault();

    const index = e.target.id;
    setArr((s) => {
      const newArr = s.slice();
      newArr[index].value = e.target.value;

      return newArr;
    });
  };

  return (
    <>
      <div>
        {arr.map((item, i) => {
          return (
            <div className={classes.cards}>
              <Card width={screenType.isMobile ? "87vw" : "67vw"} height="auto">
                <div className={classes.plot_container}>
                  <h3 className={classes.plot_no}>Plot {i + 1}</h3>
                  <div className={classes.plot_data}>
                    <Input
                      width="150px"
                      label="Plot number"
                      placeholder="Plot 1"
                    />
                    <div className={classes.group3}>
                      <Input width="150px" label="Area Size" placeholder="" />
                      <input
                        className={classes.input}
                        list="categories"
                        name="category"
                        id="category"
                        placeholder="unit"
                        style={{ width: "150px" }}
                      />
                      <datalist id="categories">
                        <option value="Edge" />
                        <option value="Firefox" />
                        <option value="Chrome" />
                        <option value="Opera" />
                        <option value="Safari" />
                      </datalist>
                    </div>

                    <div className={classes.group3}>
                      <Input width="150px" label="Front" placeholder="" />
                      <input
                        className={classes.input}
                        list="categories"
                        name="category"
                        id="category"
                        placeholder="unit"
                        style={{ width: "150px" }}
                      />
                      <datalist id="categories">
                        <option value="Edge" />
                        <option value="Firefox" />
                        <option value="Chrome" />
                        <option value="Opera" />
                        <option value="Safari" />
                      </datalist>
                    </div>

                    <div className={classes.group3}>
                      <Input width="150px" label="Depth" placeholder="" />
                      <input
                        className={classes.input}
                        list="categories"
                        name="category"
                        id="category"
                        placeholder="unit"
                        style={{ width: "150px" }}
                      />
                      <datalist id="categories">
                        <option value="Edge" />
                        <option value="Firefox" />
                        <option value="Chrome" />
                        <option value="Opera" />
                        <option value="Safari" />
                      </datalist>
                    </div>

                    <Input width="150px" label="Road" placeholder="meter" />
                    <Input
                      width="150px"
                      label=" Selling Price"
                      placeholder="456456"
                    />
                    <Input width="150px" label="Status" placeholder="Sold" />
                  </div>
                </div>
              </Card>
            </div>
          );
        })}
      </div>
      <Button label="Add Plot" type="1" onClick={addInput}>
        Add Plot
      </Button>
      <Button label="NEXT" type="1" onClick={handleClick}>
        Next
      </Button>
    </>
  );
};

export default Form3;
