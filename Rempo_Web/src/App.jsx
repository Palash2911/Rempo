import React, { useEffect, useState } from "react";
import { useLocation, Route, Routes } from "react-router-dom";
import About from "./components/About/About";
import Landing from "./components/Landing/Landing";
import Milestones from "./components/Milestones/Milestones";
import Navbar from "./components/Navbar/Navbar";
import Team from "./components/Team/Team";
import { Loader } from "./components/Ui";

const App = () => {
  const location = useLocation();
  useEffect(() => {
    window.scrollTo(0, 0);
  }, [location]);

  const [loading, setLoading] = useState(false);

  setTimeout(() => setLoading(false), 6000);

  return (
    <>
      {loading ? (
        <Loader />
      ) : (
        <div>
          <Navbar />
          <Landing />
          <Milestones />
          <About />
          <Team />
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
