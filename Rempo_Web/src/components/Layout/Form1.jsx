import React, { useContext } from "react";
import FormContext from "./formContenxt/formContext";
import { Input } from "../Ui";
import classes from "./Layout.module.css";
import { Button } from "../Ui";

const Form1 = ({ formNo, setFormNo }) => {
  const {
    owner,
    setOwner,
    category,
    setCategory,
    desc,
    setDesc,
    state,
    setState,
    district,
    setDistrict,
    taluka,
    setTaluka,
    village,
    setVillage,
  } = useContext(FormContext);
  const uploadFile = async (e) => {
    e.preventDefault();
    console.log("hell");
  };
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
          required={true}
        />
        <div className={classes.group}>
          <label for="category">Layout Category</label>
          {/* <input
            value={category}
            onChange={(e) => {
              setCategory(e.target.value);
            }}
            className={classes.input}
            list="categories"
            name="category"
            id="category"
            placeholder="Select layout category"
            required={true}
          /> */}
          <select
            value={category}
            className={classes.input}
            id="categories"
            onChange={(e) => {
              setCategory(e.target.value);
            }}
          >
            <option>Select Property Catergory</option>
            <option value="Agicultural Land">Agicultural Land</option>
            <option value="NA Land">NA Land</option>
          </select>
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
            required={true}
          ></textarea>
        </div>
        <div className={classes.group}>
          <label for="category">State</label>
          <select
            value={state}
            className={classes.input}
            id="categories"
            onChange={(e) => {
              setState(e.target.value);
            }}
          >
            <option value="Andhra Pradesh">Select State</option>
            <option value="Andhra Pradesh">Andhra Pradesh</option>
            <option value="Andaman and Nicobar Islands">
              Andaman and Nicobar Islands
            </option>
            <option value="Arunachal Pradesh">Arunachal Pradesh</option>
            <option value="Assam">Assam</option>
            <option value="Bihar">Bihar</option>
            <option value="Chandigarh">Chandigarh</option>
            <option value="Chhattisgarh">Chhattisgarh</option>
            <option value="Dadar and Nagar Haveli">
              Dadar and Nagar Haveli
            </option>
            <option value="Daman and Diu">Daman and Diu</option>
            <option value="Delhi">Delhi</option>
            <option value="Lakshadweep">Lakshadweep</option>
            <option value="Puducherry">Puducherry</option>
            <option value="Goa">Goa</option>
            <option value="Gujarat">Gujarat</option>
            <option value="Haryana">Haryana</option>
            <option value="Himachal Pradesh">Himachal Pradesh</option>
            <option value="Jammu and Kashmir">Jammu and Kashmir</option>
            <option value="Jharkhand">Jharkhand</option>
            <option value="Karnataka">Karnataka</option>
            <option value="Kerala">Kerala</option>
            <option value="Madhya Pradesh">Madhya Pradesh</option>
            <option value="Maharashtra">Maharashtra</option>
            <option value="Manipur">Manipur</option>
            <option value="Meghalaya">Meghalaya</option>
            <option value="Mizoram">Mizoram</option>
            <option value="Nagaland">Nagaland</option>
            <option value="Odisha">Odisha</option>
            <option value="Punjab">Punjab</option>
            <option value="Rajasthan">Rajasthan</option>
            <option value="Sikkim">Sikkim</option>
            <option value="Tamil Nadu">Tamil Nadu</option>
            <option value="Telangana">Telangana</option>
            <option value="Tripura">Tripura</option>
            <option value="Uttar Pradesh">Uttar Pradesh</option>
            <option value="Uttarakhand">Uttarakhand</option>
            <option value="West Bengal">West Bengal</option>
          </select>
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
            placeholder="Enter layout District"
            required={true}
          />
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
            placeholder="Enter layout Taluka"
            required={true}
          />
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
            placeholder="Enter layout Village"
            required={true}
          />
          <datalist id="categories">
            <option value="Edge" />
            <option value="Firefox" />
            <option value="Chrome" />
            <option value="Opera" />
            <option value="Safari" />
          </datalist>
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

export default Form1;
