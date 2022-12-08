import React, { useState, useEffect, useContext } from "react";
import classes from "./Layout.module.css";
import { Button, Card, Input } from "../Ui";
import useScreenType from "react-screentype-hook";
import { getAuth, onAuthStateChanged } from "firebase/auth";
import app, { db } from "../firebase_config";
import { collection, doc, setDoc, getDoc, addDoc } from "firebase/firestore";
import FormContext from "./formContenxt/formContext";

function App() {
  const screenType = useScreenType();
  useEffect(() => {
    console.log(formFields);
  });
  const [formFields, setFormFields] = useState([
    {
      plotNo: "",
      area: "",
      areaUnit: "",
      front: "",
      frontUnit: "",
      depth: "",
      depthUnit: "",
      meter: "",
      sellingPrice: "",
      plotStatus: "",
    },
  ]);

  const handleFormChange = (event, index) => {
    let data = [...formFields];
    data[index][event.target.name] = event.target.value;
    setFormFields(data);
  };

  const submit = (e) => {
    e.preventDefault();
    console.log(formFields);
  };

  const addFields = () => {
    let object = {
      plotNo: "",
      area: "",
      areaUnit: "",
      front: "",
      frontUnit: "",
      depth: "",
      depthUnit: "",
      meter: "",
      sellingPrice: "",
      plotStatus: "",
    };

    setFormFields([...formFields, object]);
  };

  const removeFields = (index) => {
    let data = [...formFields];
    data.splice(index, 1);
    setFormFields(data);
  };

  return (
    <div className="App">
      <form onSubmit={submit}>
        {formFields.map((form, index) => {
          return (
            <div className={classes.cards} key={index}>
              <Card width={screenType.isMobile ? "87vw" : "67vw"} height="auto">
                <div className={classes.plot_container}>
                  <h3 className={classes.plot_no}>Plot {index + 1}</h3>
                  <div className={classes.plot_data}>
                    <Input
                      width="150px"
                      label="Plot number"
                      placeholder="Plot 1"
                      name="plotNo"
                      value={form.plotNo}
                      onChange={(event) => handleFormChange(event, index)}
                    />
                    <div className={classes.group3}>
                      <Input
                        width="150px"
                        label="Area Size"
                        placeholder=""
                        name="area"
                        value={form.area}
                        onChange={(event) => handleFormChange(event, index)}
                      />
                      <input
                        className={classes.input}
                        list="areaUnit"
                        name="areaUnit"
                        id="areaUnit"
                        placeholder="unit"
                        value={form.areaUnit}
                        onChange={(event) => handleFormChange(event, index)}
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
                      <Input
                        width="150px"
                        label="Front"
                        placeholder=""
                        name="front"
                        value={form.front}
                        onChange={(event) => handleFormChange(event, index)}
                      />
                      <input
                        className={classes.input}
                        list="frontUnit"
                        name="frontUnit"
                        id="frontUnit"
                        placeholder="frontUnit"
                        value={form.frontUnit}
                        onChange={(event) => handleFormChange(event, index)}
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
                      <Input
                        width="150px"
                        label="Depth"
                        placeholder=""
                        name="depth"
                        value={form.depth}
                        onChange={(event) => handleFormChange(event, index)}
                      />
                      <input
                        className={classes.input}
                        list="depthUnit"
                        name="depthUnit"
                        id="depthUnit"
                        placeholder="depthUnit"
                        value={form.depthUnit}
                        onChange={(event) => handleFormChange(event, index)}
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

                    <Input
                      width="150px"
                      label="Road"
                      placeholder="meter"
                      name="meter"
                      value={form.meter}
                      onChange={(event) => handleFormChange(event, index)}
                    />
                    <Input
                      width="150px"
                      label=" Selling Price"
                      placeholder="456456"
                      name="sellingPrice"
                      value={form.sellingPrice}
                      onChange={(event) => handleFormChange(event, index)}
                    />
                    <Input
                      width="150px"
                      label="Status"
                      placeholder="Sold"
                      name="plotStatus"
                      value={form.plotStatus}
                      onChange={(event) => handleFormChange(event, index)}
                    />
                  </div>
                </div>
              </Card>
            </div>
          );
        })}
      </form>
      <div className={classes.addPlotBtn}>
        <Button label="Add Plot" type="1" onClick={addFields}>
          Add Plot
        </Button>
      </div>
    </div>
  );
}

export default App;
