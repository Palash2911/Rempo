import React from "react";
import About from "../components/About/About";
import Landing from "../components/Landing/Landing";
import Milestones from "../components/Milestones/Milestones";
import Team from "../components/Team/Team";

const Homepage = () => {
  return (
    <>
      <Landing />
      <Milestones />
      <About />
      <Team />
    </>
  );
};

export default Homepage;
