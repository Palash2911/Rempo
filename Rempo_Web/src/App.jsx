import React, { useEffect, useState } from "react";
import { useLocation, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar/Navbar";
import { Loader } from "./components/Ui";

const App = () => {
  const location = useLocation();
  useEffect(() => {
    window.scrollTo(0, 0);
  }, [location]);

  const [loading, setLoading] = useState(true);

  setTimeout(() => setLoading(false), 6000);

  return (
    <>
      {loading ? (
        <Loader />
      ) : (
        <div>
          <Navbar />
          <Routes>
            <Route path="/" element={<></>} />
            <Route path="/" element={<></>} />
            <Route path="/" element={<></>} />
          </Routes>
        </div>
      )}
    </>
  );
};

export default App;
