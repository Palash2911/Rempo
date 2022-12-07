import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { FormProvider } from "./components/Layout/formContenxt/formContext";

ReactDOM.render(
  <FormProvider>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </FormProvider>,
  document.getElementById("root")
);
