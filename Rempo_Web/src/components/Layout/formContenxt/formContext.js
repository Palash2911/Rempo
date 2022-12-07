import { useState, useContext, createContext } from "react";

const FormContext = createContext();

export const FormProvider = ({ children }) => {
  const [owner, setOwner] = useState("");
  const [category, setCategory] = useState("");
  const [desc, setDesc] = useState("");
  const [district, setDistrict] = useState("");
  const [taluka, setTaluka] = useState("");
  const [village, setVillage] = useState("");
  const [surveyNo, setSurveyNo] = useState("");
  const [layoutLocation, setLayoutLocation] = useState("");
  const [Doc1, setDoc1] = useState("");
  const [Doc2, setDoc2] = useState("");
  const [Doc3, setDoc3] = useState("");
  const [Doc4, setDoc4] = useState("");

  return (
    <FormContext.Provider
      value={{
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
      }}
    >
      {children}
    </FormContext.Provider>
  );
};

export default FormContext;
