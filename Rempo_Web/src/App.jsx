import React, { useEffect, useState } from "react";
import { useLocation, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar/Navbar";
import Footer from "./components/Footer/Footer";
import { Loader } from "./components/Ui";
import Login from "./components/Login/Login";
import Homepage from "./pages/Homepage";
import Profile from "./components/Profile/Profile";
import UserProfile from "./components/UserProfile/UserProfile";

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
          <Routes>
            <Route path="/" element={<Homepage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/Profile" element={<Profile />} />
            <Route path="/userProfile" element={<UserProfile />} />
          </Routes>
          <Footer />
        </div>
      )}
    </>
  );
};

export default App;
