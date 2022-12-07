import React, { useState } from "react";
import classes from "./Layout.module.css";
import { Button, Card, Input } from "../Ui";
import useScreenType from "react-screentype-hook";

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
    </>
  );
};

export default Form3;
