import { useState, createContext } from "react";

const FormContext = createContext();

export const FormProvider = ({ children }) => {
  const [owner, setOwner] = useState("");
  const [category, setCategory] = useState("");
  const [desc, setDesc] = useState("");
  const [state, setState] = useState("");
  const [district, setDistrict] = useState("");
  const [taluka, setTaluka] = useState("");
  const [village, setVillage] = useState("");
  const [surveyNo, setSurveyNo] = useState("");
  const [layoutLocation, setLayoutLocation] = useState("");
  const [Doc1, setDoc1] = useState("");
  const [Doc2, setDoc2] = useState("");
  const [Doc3, setDoc3] = useState("");
  const [Doc4, setDoc4] = useState("");
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

  return (
    <FormContext.Provider
      value={{
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
        formFields,
        setFormFields,
      }}
    >
      {children}
    </FormContext.Provider>
  );
};

export default FormContext;
