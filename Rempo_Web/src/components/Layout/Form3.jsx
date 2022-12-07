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
            <Card width={screenType.isMobile ? "87vw" : "67vw"} height="auto">
              <div className={classes.plot_container}>
                <h3>Plot {i + 1}</h3>
                <div className={classes.plot_data}>
                  <Input
                    width="150px"
                    label="Plot number"
                    placeholder="Plot 1"
                  />
                  <Input width="150px" label="Road" placeholder="meter" />
                  <Input
                    width="150px"
                    label="Expected Selling Rate"
                    placeholder="456456"
                  />
                  <Input width="150px" label="Status" placeholder="Sold" />
                </div>
              </div>
            </Card>
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
