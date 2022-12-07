import React from "react";
import { Input } from "../Ui";
import classes from "./Layout.module.css";

const Form1 = () => {
  return (
    <>
      <div className={classes.form_group}>
        <Input placeholder="John Doe" label="Owner Name" />
        <div className={classes.group}>
          <label for="category">Layout Category</label>
          <input
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
