import React, { useContext } from "react";
import classes from "./Layout.module.css";
import { Button, Card, Input } from "../Ui";
import useScreenType from "react-screentype-hook";
import FormContext from "./formContenxt/formContext";

function App() {
  const screenType = useScreenType();
  const { formFields, setFormFields } = useContext(FormContext);

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
                      required={true}
                    />
                    <div className={classes.group3}>
                      <Input
                        width="150px"
                        label="Area Size"
                        placeholder="123"
                        name="area"
                        value={form.area}
                        onChange={(event) => handleFormChange(event, index)}
                        required={true}
                        type="number"
                      />
                      <select className={classes.input} id="areaUnit">
                        <option>Select Unit</option>
                        <option value="Sq Ft">Sq Ft</option>
                        <option value="H.R">H.R</option>
                        <option value="Sq Mt">Sq Mt</option>
                        <option value="Acres">Acres</option>
                      </select>
                    </div>

                    <div className={classes.group3}>
                      <Input
                        width="150px"
                        label="Front"
                        placeholder="123"
                        name="front"
                        value={form.front}
                        onChange={(event) => handleFormChange(event, index)}
                        required={true}
                        type="number"
                      />
                      <select className={classes.input} id="frontUnit">
                        <option>Select Unit</option>
                        <option value="Ft">Ft</option>
                        <option value="Mt">Mt</option>
                      </select>
                    </div>

                    <Input
                      width="150px"
                      label="Depth"
                      placeholder="12"
                      name="depth"
                      value={form.depth}
                      onChange={(event) => handleFormChange(event, index)}
                      required={true}
                      type="number"
                    />

                    <Input
                      width="150px"
                      label="Road"
                      placeholder="meter"
                      name="meter"
                      value={form.meter}
                      onChange={(event) => handleFormChange(event, index)}
                      required={true}
                      type="number"
                    />
                    <Input
                      width="150px"
                      label=" Selling Price"
                      placeholder="456"
                      name="sellingPrice"
                      value={form.sellingPrice}
                      onChange={(event) => handleFormChange(event, index)}
                      required={true}
                      type="number"
                    />
                    <select className={classes.inputsbidselect} id="frontUnit">
                      <option value="Ft">Ft</option>
                      <option value="Mt">Mt</option>
                    </select>
                    <Input
                      width="150px"
                      label="Status"
                      placeholder="Sold"
                      name="plotStatus"
                      value={form.plotStatus}
                      onChange={(event) => handleFormChange(event, index)}
                      required={true}
                    />
                  </div>
                  <div style={{ marginTop: "20px" }}>
                    <Button
                      label="Remove Plot"
                      type="2"
                      onClick={() => removeFields(index)}
                    >
                      Remove Plot
                    </Button>
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
