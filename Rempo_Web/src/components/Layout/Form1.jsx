import React, { useContext } from "react";
import FormContext from "./formContenxt/formContext";
import { Input } from "../Ui";
import classes from "./Layout.module.css";

const Form1 = () => {
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
  return (
    <>
      <div className={classes.form_group}>
        <Input
          value={owner}
          onChange={(e) => {
            setOwner(e.target.value);
          }}
          placeholder="John Doe"
          label="Owner Name"
        />
        <div className={classes.group}>
          <label for="category">Layout Category</label>
          <input
            value={category}
            onChange={(e) => {
              setCategory(e.target.value);
            }}
            className={classes.input}
            list="categories"
            name="category"
            id="category"
            placeholder="Select layout category"
          />
          <datalist id="categories">
            <option value="Edge" />
            <option value="Firefox" />
            <option value="Chrome" />
            <option value="Opera" />
            <option value="Safari" />
          </datalist>
        </div>
        <div className={classes.group}>
          <label for="description">Layout Description</label>
          <textarea
            value={desc}
            onChange={(e) => {
              setDesc(e.target.value);
            }}
            name="description"
            id="description"
            cols="60"
            rows="10"
            className={classes.input2}
            placeholder="Description of layout"
          ></textarea>
        </div>
        <div className={classes.group}>
          <label for="category">District</label>
          <input
            value={district}
            onChange={(e) => {
              setDistrict(e.target.value);
            }}
            className={classes.input}
            list="categories"
            name="category"
            id="category"
            placeholder="Select layout District"
          />
          <datalist id="categories">
            <option value="Edge" />
            <option value="Firefox" />
            <option value="Chrome" />
            <option value="Opera" />
            <option value="Safari" />
          </datalist>
        </div>
        <div className={classes.group}>
          <label for="category">Taluka</label>
          <input
            value={taluka}
            onChange={(e) => {
              setTaluka(e.target.value);
            }}
            className={classes.input}
            list="categories"
            name="category"
            id="category"
            placeholder="Select layout Taluka"
          />
          <datalist id="categories">
            <option value="Edge" />
            <option value="Firefox" />
            <option value="Chrome" />
            <option value="Opera" />
            <option value="Safari" />
          </datalist>
        </div>
        <div className={classes.group}>
          <label for="category">Village</label>
          <input
            value={village}
            onChange={(e) => {
              setVillage(e.target.value);
            }}
            className={classes.input}
            list="categories"
            name="category"
            id="category"
            placeholder="Select layout Village"
          />
          <datalist id="categories">
            <option value="Edge" />
            <option value="Firefox" />
            <option value="Chrome" />
            <option value="Opera" />
            <option value="Safari" />
          </datalist>
        </div>
      </div>
    </>
  );
};

export default Form1;
